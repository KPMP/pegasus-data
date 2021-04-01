package org.kpmp.umap;

import java.util.ArrayList;
import java.util.List;

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

	public List<? extends UmapPoint> getUmapPoints(String dataType, String geneSymbol, String requestTissueType)
			throws JSONException, Exception {
		JSONObject geneExpressionValues = expressionService.getGeneExpressionValues(dataType, geneSymbol);
		DataTypeEnum dataTypeEnum = DataTypeEnum.fromAbbreviation(dataType);
		List<? extends UmapPoint> umapPoints = new ArrayList<>();
		TissueTypeEnum tissueType = TissueTypeEnum.fromRequestType(requestTissueType);

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

		for (UmapPoint umapPoint : umapPoints) {
			String barcode = umapPoint.getBarcode();
			if (geneExpressionValues.has(barcode)) {
				umapPoint.setExpressionValue(geneExpressionValues.getDouble(barcode));
			} else {
				umapPoint.setExpressionValue(0d);
			}
		}
		return umapPoints;
	}

}
