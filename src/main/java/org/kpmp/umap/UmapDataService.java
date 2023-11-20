package org.kpmp.umap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.kpmp.DataTypeEnum;
import org.kpmp.TissueTypeEnum;
import org.kpmp.geneExpression.SNSCExpressionDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UmapDataService {

	private SNSCExpressionDataService expressionService;
	private SNMetadataRepository snMetadataRepo;
	private SCMetadataRepository scMetadataRepo;

	@Autowired
	public UmapDataService(SCMetadataRepository scMetadataRepo, SNMetadataRepository snMetadataRepo,
			SNSCExpressionDataService expressionService) {
		this.scMetadataRepo = scMetadataRepo;
		this.snMetadataRepo = snMetadataRepo;
		this.expressionService = expressionService;
	}

	public PlotData getPlotData(String dataType, String geneSymbol, String requestTissueType)
			throws JSONException, Exception {
		JSONObject geneExpressionValues = expressionService.getGeneExpressionValues(dataType, geneSymbol);
		DataTypeEnum dataTypeEnum = DataTypeEnum.fromAbbreviation(dataType);
		List<? extends UmapPoint> umapPoints = new ArrayList<>();
		TissueTypeEnum tissueType = TissueTypeEnum.fromRequestType(requestTissueType);

		umapPoints = getUmapPoints(dataTypeEnum, umapPoints, tissueType);

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

		List<ReferenceCluster> referenceClusterList = new ArrayList<>(referenceClusters.values());
		return new PlotData(referenceClusterList,
				Arrays.asList(featureDataWithExpressionValues, featureDataWithNoExpressionValues));
	}

	private List<? extends UmapPoint> getUmapPoints(DataTypeEnum dataTypeEnum, List<? extends UmapPoint> umapPoints,
			TissueTypeEnum tissueType) {
		if (tissueType == TissueTypeEnum.ALL) {
			if (dataTypeEnum.equals(DataTypeEnum.SINGLE_CELL)) {
				int pointCount = scMetadataRepo.findCount();
				int limit = (int) Math.round(pointCount*.3);
				umapPoints = scMetadataRepo.findLimited(limit);
			} else if (dataTypeEnum.equals(DataTypeEnum.SINGLE_NUCLEUS)) {
				int pointCount = snMetadataRepo.findCount();
				int limit = (int) Math.round(pointCount*.3);
				umapPoints = snMetadataRepo.findLimited(limit);
			}
		} else if (tissueType != TissueTypeEnum.UNKNOWN) {
			if (dataTypeEnum.equals(DataTypeEnum.SINGLE_CELL)) {
				int pointCount = scMetadataRepo.findCount();
				int limit = (int) Math.round(pointCount*.3);
				umapPoints = scMetadataRepo.findLimitedWithTissueType(tissueType.getParticipantTissueType(), limit);
			} else if (dataTypeEnum.equals(DataTypeEnum.SINGLE_NUCLEUS)) {
				int pointCount = snMetadataRepo.findCount();
				int limit = (int) Math.round(pointCount*.3);
				umapPoints = snMetadataRepo.findLimitedWithTissueType(tissueType.getParticipantTissueType(), limit);
			}
		}
		return umapPoints;
	}

}
