package org.kpmp.geneExpression;

import org.json.JSONException;
import org.json.JSONObject;
import org.kpmp.FullDataTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SNSCExpressionDataServiceNewData {

	private SNExpressionDataRepositoryNewData snRepo;
	private SCExpressionDataRepositoryNewData scRepo;

	@Autowired
	public SNSCExpressionDataServiceNewData(SNExpressionDataRepositoryNewData snRepo, SCExpressionDataRepositoryNewData scRepo) {
		this.snRepo = snRepo;
		this.scRepo = scRepo;
	}

	public JSONObject getGeneExpressionValues(String dataType, String geneSymbol) throws JSONException, Exception {
		if (dataType.equals(FullDataTypeEnum.SINGLE_NUCLEUS.getAbbreviation())) {
			SNExpressionDataNewData expressionData = snRepo.findByGeneSymbol(geneSymbol);
			return expressionData.getExpressionDataAsJson();
		} else if (dataType.equals(FullDataTypeEnum.SINGLE_CELL.getAbbreviation())) {
			SCExpressionDataNewData expressionData = scRepo.findByGeneSymbol(geneSymbol);
			return expressionData.getExpressionDataAsJson();
		}
		throw new Exception("Invalid data type: " + dataType);
	}

}
