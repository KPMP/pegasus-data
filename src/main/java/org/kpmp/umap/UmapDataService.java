package org.kpmp.umap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;
import org.kpmp.EnrollmentCategoryEnum;
import org.kpmp.FullDataTypeEnum;
import org.kpmp.geneExpression.SNSCExpressionDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UmapDataService {

	private SNSCExpressionDataService expressionService;
	private SNMetadataRepository snMetadataRepo;
	private SCMetadataRepository scMetadataRepo;
    private SNMetadataRepositoryNewData snMetadataRepoNewData;
    private SCMetadataRepositoryNewData scMetadataRepoNewData;

	@Autowired
	public UmapDataService(SCMetadataRepository scMetadataRepo, SCMetadataRepositoryNewData scMetadataRepoNewData, SNMetadataRepository snMetadataRepo, 
            SNMetadataRepositoryNewData snMetadataRepoNewData,
			SNSCExpressionDataService expressionService) {
		this.scMetadataRepo = scMetadataRepo;
		this.snMetadataRepo = snMetadataRepo;
		this.expressionService = expressionService;
        this.snMetadataRepoNewData = snMetadataRepoNewData;
        this.scMetadataRepoNewData = scMetadataRepoNewData;
	}

	public PlotData getPlotData(String dataType, String geneSymbol, String requestEnrollmentCategory, Boolean newData)
			throws JSONException, Exception {
		JSONObject geneExpressionValues = expressionService.getGeneExpressionValues(dataType, geneSymbol, newData);
		FullDataTypeEnum dataTypeEnum = FullDataTypeEnum.fromAbbreviation(dataType);
		List<? extends UmapPoint> umapPoints = new ArrayList<>();
		EnrollmentCategoryEnum enrollmentCategory = EnrollmentCategoryEnum.fromRequestType(requestEnrollmentCategory);

		umapPoints = getUmapPoints(dataTypeEnum, umapPoints, enrollmentCategory, newData);

		Map<String, ReferenceCluster> referenceClusters = new HashMap<>();
		FeatureData featureDataWithExpressionValues = new FeatureData();
		FeatureData featureDataWithNoExpressionValues = new FeatureData();

		for (UmapPoint umapPoint : umapPoints) {
			double umapX = umapPoint.getUmapX();
			double umapY = umapPoint.getUmapY();

			if (referenceClusters.containsKey(umapPoint.getClusterAbbreviation())) {
				ReferenceCluster referenceCluster = referenceClusters.get(umapPoint.getClusterAbbreviation());
				referenceCluster.addXValue(umapX);
				referenceCluster.addYValue(umapY);
				referenceCluster.setClusterName(umapPoint.getClusterName());
				referenceCluster.setClusterAbbreviation(umapPoint.getClusterAbbreviation());
			} else {
				ReferenceCluster referenceCluster = new ReferenceCluster(umapPoint.getClusterAbbreviation(),
						umapPoint.getClusterColor());
				referenceCluster.addXValue(umapX);
				referenceCluster.addYValue(umapY);
                referenceCluster.setClusterName(umapPoint.getClusterName());
                referenceCluster.setClusterAbbreviation(umapPoint.getClusterAbbreviation());
                referenceClusters.put(umapPoint.getClusterAbbreviation(), referenceCluster);
			}

			String barcode = umapPoint.getBarcode();
			if (geneExpressionValues.has(barcode)) {
			    Double expressionValue = geneExpressionValues.getDouble(barcode);
				featureDataWithExpressionValues.addXValue(umapX);
				featureDataWithExpressionValues.addYValue(umapY);
				featureDataWithExpressionValues.addExpression(expressionValue);
				featureDataWithExpressionValues.addHoverDisplay(Double.toString(expressionValue) + "<br>" + umapPoint.getClusterName());

            } else {
				featureDataWithNoExpressionValues.addXValue(umapX);
				featureDataWithNoExpressionValues.addYValue(umapY);
				featureDataWithNoExpressionValues.addExpression(0d);
                featureDataWithNoExpressionValues.addHoverDisplay(umapPoint.getClusterName());
            }
		}

		if (dataTypeEnum.equals(FullDataTypeEnum.SINGLE_CELL)
			&& featureDataWithExpressionValues.getExpression().size() == 0 
			&& geneExpressionValues.length() <= 200) {
			Iterator<String> keys = geneExpressionValues.keys();
			while(keys.hasNext()) {
				String barcode = keys.next();
				Double expressionValue = geneExpressionValues.getDouble(barcode);
				UmapPoint umapPoint = scMetadataRepo.findByBarcode(barcode);
				featureDataWithExpressionValues.addXValue(umapPoint.getUmapX());
				featureDataWithExpressionValues.addYValue(umapPoint.getUmapY());
				featureDataWithExpressionValues.addExpression(expressionValue);
				featureDataWithExpressionValues.addHoverDisplay(Double.toString(expressionValue) + "<br>" + umapPoint.getClusterName());
			}
		}

		List<ReferenceCluster> referenceClusterList = new ArrayList<>(referenceClusters.values());
		return new PlotData(referenceClusterList,
				Arrays.asList(featureDataWithExpressionValues, featureDataWithNoExpressionValues));
	}

	private List<? extends UmapPoint> getUmapPoints(FullDataTypeEnum dataTypeEnum, List<? extends UmapPoint> umapPoints,
			EnrollmentCategoryEnum enrollmentCategory, Boolean newData) {
		if (enrollmentCategory == EnrollmentCategoryEnum.ALL) {
			if (dataTypeEnum.equals(FullDataTypeEnum.SINGLE_CELL)) {
                if(newData != null && newData) {
                    int pointCount = scMetadataRepoNewData.findCount();
                    int limit = (int) Math.round(pointCount*.3);
                    umapPoints = scMetadataRepoNewData.findLimited(limit);
                }else{
                    int pointCount = scMetadataRepo.findCount();
                    int limit = (int) Math.round(pointCount*.3);
                    umapPoints = scMetadataRepo.findLimited(limit);
                }
			} else if (dataTypeEnum.equals(FullDataTypeEnum.SINGLE_NUCLEUS)) {
                if (newData != null && newData) {
                    int pointCount = snMetadataRepoNewData.findCount();
                    int limit = (int) Math.round(pointCount*.3);
                    umapPoints = snMetadataRepoNewData.findLimited(limit);
                }else{
                    int pointCount = snMetadataRepo.findCount();
                    int limit = (int) Math.round(pointCount*.3);
                    umapPoints = snMetadataRepo.findLimited(limit);
                }
			}
		} else if (enrollmentCategory != EnrollmentCategoryEnum.UNKNOWN) {
			if (dataTypeEnum.equals(FullDataTypeEnum.SINGLE_CELL)) {
				int pointCount = scMetadataRepo.findCount();
				int limit = (int) Math.round(pointCount*.3);
				umapPoints = scMetadataRepo.findLimitedWithEnrollmentCategory(enrollmentCategory.getParticipantEnrollmentCategory(), limit);
			} else if (dataTypeEnum.equals(FullDataTypeEnum.SINGLE_NUCLEUS)) {
                if (newData != null && newData) { 
                    int pointCount = snMetadataRepoNewData.findCount();
                    int limit = (int) Math.round(pointCount*.3);
                    umapPoints = snMetadataRepoNewData.findLimitedWithEnrollmentCategory(enrollmentCategory.getParticipantEnrollmentCategory(), limit);
                }else{
                    int pointCount = snMetadataRepo.findCount();
                    int limit = (int) Math.round(pointCount*.3);
                    umapPoints = snMetadataRepo.findLimitedWithEnrollmentCategory(enrollmentCategory.getParticipantEnrollmentCategory(), limit);
                }
			}
		}
		return umapPoints;
	}

}
