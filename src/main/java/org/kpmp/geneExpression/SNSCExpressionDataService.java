package org.kpmp.geneExpression;

import org.json.JSONException;
import org.json.JSONObject;
import org.kpmp.FullDataTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SNSCExpressionDataService {

	private SNExpressionDataRepository snRepo;
    private SNExpressionDataRepositoryNewData snRepoNewData;
    private SCExpressionDataRepositoryNewData scRepoNewData;
	private SCExpressionDataRepository scRepo;

	@Autowired
	public SNSCExpressionDataService(SNExpressionDataRepository snRepo, SNExpressionDataRepositoryNewData snRepoNewData, 
    SCExpressionDataRepository scRepo, SCExpressionDataRepositoryNewData scRepoNewData) {
        this.snRepoNewData = snRepoNewData;
		this.snRepo = snRepo;
		this.scRepo = scRepo;
        this.scRepoNewData = scRepoNewData;
	}

	public JSONObject getGeneExpressionValues(String dataType, String geneSymbol, Boolean newData) throws JSONException, Exception {
		if (dataType.equals(FullDataTypeEnum.SINGLE_NUCLEUS.getAbbreviation())) {
            if (newData != null && newData) {
                SNExpressionDataNewData expressionData = snRepoNewData.findByGeneSymbol(geneSymbol);
                return expressionData.getExpressionDataAsJson();
            }else{
                SNExpressionData expressionData = snRepo.findByGeneSymbol(geneSymbol);
                return expressionData.getExpressionDataAsJson();

            }
		} else if (dataType.equals(FullDataTypeEnum.SINGLE_CELL.getAbbreviation())) {
            if(newData != null && newData) {
                SCExpressionDataNewData expressionData = scRepoNewData.findByGeneSymbol(geneSymbol);
                return expressionData.getExpressionDataAsJson();
            }else{
                SCExpressionData expressionData = scRepo.findByGeneSymbol(geneSymbol);
                return expressionData.getExpressionDataAsJson();
            }
		}
		throw new Exception("Invalid data type: " + dataType);
	}

}
