package org.kpmp.geneExpression;

import org.json.JSONException;
import org.json.JSONObject;
import org.kpmp.DataTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpressionDataService {

	private SNExpressionDataRepository snRepo;
	private SCExpressionDataRepository scRepo;

	@Autowired
	public ExpressionDataService(SNExpressionDataRepository snRepo, SCExpressionDataRepository scRepo) {
		this.snRepo = snRepo;
		this.scRepo = scRepo;
	}

	public JSONObject getGeneExpressionValues(String dataType, String geneSymbol) throws JSONException, Exception {
		if (dataType.equals(DataTypeEnum.SINGLE_NUCLEUS.getAbbreviation())) {
			SNExpressionData expressionData = snRepo.findByGeneSymbol(geneSymbol);
			return expressionData.getExpressionDataAsJson();
		} else if (dataType.equals(DataTypeEnum.SINGLE_CELL.getAbbreviation())) {
			SCExpressionData expressionData = scRepo.findByGeneSymbol(geneSymbol);
			return expressionData.getExpressionDataAsJson();
		}
		throw new Exception("Invalid data type: " + dataType);
	}

}
