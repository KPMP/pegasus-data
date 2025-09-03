package org.kpmp.geneExpression;

import org.json.JSONException;
import org.json.JSONObject;
import org.kpmp.FullDataTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SNSCExpressionDataService {

	private SNExpressionDataRepository snRepo;
	private SCExpressionDataRepository scRepo;

	@Autowired
	public SNSCExpressionDataService(SNExpressionDataRepository snRepo, SCExpressionDataRepository scRepo) {
		this.snRepo = snRepo;
		this.scRepo = scRepo;
	}

	public JSONObject getGeneExpressionValues(String dataType, String geneSymbol) throws JSONException, Exception {
		if (dataType.equals(FullDataTypeEnum.SINGLE_NUCLEUS.getAbbreviation())) {
            System.out.println("Getting SN Expression Data for " + geneSymbol);
            SNExpressionData expressionData = snRepo.findByGeneSymbol(geneSymbol);
            System.out.println("Got SN Expression Data for " + geneSymbol);
            System.out.println("Expression Data: " + expressionData.getExpressionDataAsJson().toString());
			return expressionData.getExpressionDataAsJson();
		} else if (dataType.equals(FullDataTypeEnum.SINGLE_CELL.getAbbreviation())) {
			SCExpressionData expressionData = scRepo.findByGeneSymbol(geneSymbol);
			return expressionData.getExpressionDataAsJson();
		}
		throw new Exception("Invalid data type: " + dataType);
	}

}
