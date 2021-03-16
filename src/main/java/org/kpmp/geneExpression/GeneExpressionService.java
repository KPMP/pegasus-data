package org.kpmp.geneExpression;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneExpressionService {

    private SCRNAGeneExpressionRepository scrnaGeneExpressionRepository;
    private SNRNAGeneExpressionRepository snrnaGeneExpressionRepository;

    @Autowired
    public GeneExpressionService(SCRNAGeneExpressionRepository scrnaGeneExpressionRepository, SNRNAGeneExpressionRepository snrnaGeneExpressionRepository) {
       this.scrnaGeneExpressionRepository = scrnaGeneExpressionRepository;
       this.snrnaGeneExpressionRepository = snrnaGeneExpressionRepository;
    }

    public List<SNRNAGeneExpressionValue> getSNExpressionByGene(String geneSymbol) {
        return snrnaGeneExpressionRepository.findByGeneEquals(geneSymbol);
    }

    public List<SCRNAGeneExpressionValue> getSCExpressionByGene(String geneSymbol) {
        return scrnaGeneExpressionRepository.findByGeneEquals(geneSymbol);
    }

}
