package org.kpmp.umap;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.kpmp.geneExpression.ExpressionDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UmapDataService {

	private UmapPointRepository umapPointRepo;
	private ExpressionDataService expressionService;

	@Autowired
	public UmapDataService(UmapPointRepository umapPointRepo, ExpressionDataService expressionService) {
		this.umapPointRepo = umapPointRepo;
		this.expressionService = expressionService;
	}

	public List<UmapPoint> getUmapPoints(String dataType, String geneSymbol) throws JSONException, Exception {
		JSONObject geneExpressionValues = expressionService.getGeneExpressionValues(dataType, geneSymbol);
		List<UmapPoint> umapPoints = umapPointRepo.findByDataType(dataType);
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
