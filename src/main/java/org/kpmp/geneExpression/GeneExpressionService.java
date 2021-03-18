package org.kpmp.geneExpression;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GeneExpressionService {

    private final String SC_DATATYPE = "sc";
    private final String SN_DATATYPE = "sn";

    private SCRNAGeneExpressionRepository scrnaGeneExpressionRepository;
    private SNRNAGeneExpressionRepository snrnaGeneExpressionRepository;

    @Autowired
    public GeneExpressionService(SCRNAGeneExpressionRepository scrnaGeneExpressionRepository, SNRNAGeneExpressionRepository snrnaGeneExpressionRepository) {
       this.scrnaGeneExpressionRepository = scrnaGeneExpressionRepository;
       this.snrnaGeneExpressionRepository = snrnaGeneExpressionRepository;
    }

    public List<? extends GeneExpressionValue> getByDataTypeTissueTypeAndGene(String dataType, String geneSymbol, String tissueType) {
        List<? extends GeneExpressionValue> results;
        switch (dataType) {
            case SC_DATATYPE:
                results = scrnaGeneExpressionRepository.findByTissueAndGeneAllClusters(geneSymbol, tissueType);
                break;
            case SN_DATATYPE:
                results = snrnaGeneExpressionRepository.findByTissueAndGeneAllClusters(geneSymbol, tissueType);
                break;
            default:
                List allResults = new ArrayList<>();
                allResults.addAll(scrnaGeneExpressionRepository.findByTissueAndGeneAllClusters(geneSymbol, tissueType));
                allResults.addAll(snrnaGeneExpressionRepository.findByTissueAndGeneAllClusters(geneSymbol, tissueType));
                results = allResults;
        }
        return results;
    }
}
