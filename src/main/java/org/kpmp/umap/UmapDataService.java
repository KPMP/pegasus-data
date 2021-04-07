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
import org.kpmp.geneExpression.ExpressionDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UmapDataService {

	private ExpressionDataService expressionService;
	private SNMetadataRepository snMetadataRepo;
	private SCMetadataRepository scMetadataRepo;

	@Autowired
	public UmapDataService(SCMetadataRepository scMetadataRepo, SNMetadataRepository snMetadataRepo,
			ExpressionDataService expressionService) {
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

			if (referenceClusters.containsKey(umapPoint.getClusterName())) {
				ReferenceCluster referenceCluster = referenceClusters.get(umapPoint.getClusterName());
				referenceCluster.addXValue(umapX);
				referenceCluster.addYValue(umapY);
			} else {
				ReferenceCluster referenceCluster = new ReferenceCluster(umapPoint.getClusterName(),
						umapPoint.getClusterColor());
				referenceCluster.addXValue(umapX);
				referenceCluster.addYValue(umapY);
				referenceClusters.put(umapPoint.getClusterName(), referenceCluster);
			}

			String barcode = umapPoint.getBarcode();
			if (geneExpressionValues.has(barcode)) {
				featureDataWithExpressionValues.addXValue(umapX);
				featureDataWithExpressionValues.addYValue(umapY);
				featureDataWithExpressionValues.addExpression(geneExpressionValues.getDouble(barcode));
			} else {
				featureDataWithNoExpressionValues.addXValue(umapX);
				featureDataWithNoExpressionValues.addYValue(umapY);
				featureDataWithNoExpressionValues.addExpression(0d);
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
				umapPoints = scMetadataRepo.findAll();
			} else if (dataTypeEnum.equals(DataTypeEnum.SINGLE_NUCLEUS)) {
				umapPoints = snMetadataRepo.findAll();
			}
		} else if (tissueType != TissueTypeEnum.UNKNOWN) {
			if (dataTypeEnum.equals(DataTypeEnum.SINGLE_CELL)) {
				umapPoints = scMetadataRepo.findByTissueType(tissueType.getParticipantTissueType());
			} else if (dataTypeEnum.equals(DataTypeEnum.SINGLE_NUCLEUS)) {
				umapPoints = snMetadataRepo.findByTissueType(tissueType.getParticipantTissueType());
			}
		}
		return umapPoints;
	}

}
