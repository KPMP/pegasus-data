package org.kpmp.autocomplete;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.kpmp.cellType.CellType;
import org.kpmp.cellType.CellTypeRepository;
import org.kpmp.gene.GeneService;
import org.kpmp.gene.MyGeneInfoHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutocompleteService {

    public static final String TYPE_CELL_TYPE = "cell_type";
    public static final String TYPE_GENE = "gene";

    private GeneService geneService;
    private CellTypeRepository cellTypeRepository;

    @Autowired
    public AutocompleteService(GeneService geneService, CellTypeRepository cellTypeRepository) {
        this.geneService = geneService;
        this.cellTypeRepository = cellTypeRepository;
    }

    public List<AutocompleteResult> query(String searchTerm) throws IOException {
        List<MyGeneInfoHit> myGeneInfoHits = geneService.querySymbolAndAlias(searchTerm);
        List<CellType> cellTypes = cellTypeRepository.findByCellTypeContainingOrSynonymContaining(searchTerm);
        List<AutocompleteResult> myGeneResults = convertMyGeneInfoHitsToAutocompleteResults(myGeneInfoHits);
        List<AutocompleteResult> cellTypeResults = convertCellTypesToAutocompleteResults(cellTypes);
        myGeneResults.addAll(cellTypeResults);
        return myGeneResults;
    }

    public List<AutocompleteResult> convertMyGeneInfoHitsToAutocompleteResults(List<MyGeneInfoHit> hits) {
        List<AutocompleteResult> autocompleteResults = new ArrayList<>();
        for (MyGeneInfoHit hit : hits) {
            autocompleteResults.add(new AutocompleteResult(hit.getSymbol(), hit.getName(), hit.getId(), TYPE_GENE, hit.getAlias()));
        }
        return autocompleteResults;
    }

    public List<AutocompleteResult> convertCellTypesToAutocompleteResults(List<CellType> cellTypes) {
        List<AutocompleteResult> autocompleteResults = new ArrayList<>();
        for (CellType cellType : cellTypes) {
            autocompleteResults.add(new AutocompleteResult(cellType.getCellType(), null, Integer.toString(cellType.getCellTypeId()), TYPE_CELL_TYPE, cellType.getSynonymStringList()));
        }
        return autocompleteResults;
    }

}
