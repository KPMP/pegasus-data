package org.kpmp.autocomplete;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.kpmp.cellType.CellType;
import org.kpmp.cellType.CellTypeRepository;
import org.kpmp.gene.GeneService;
import org.kpmp.gene.MyGeneInfoHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutocompleteService {

	public static final String TYPE_CELL_TYPE = "cell_type";
	public static final String TYPE_GENE = "gene";

	private GeneService geneService;
	private CellTypeRepository cellTypeRepository;
	private Logger logger = LoggerFactory.getLogger(AutocompleteService.class);

	@Autowired
	public AutocompleteService(GeneService geneService, CellTypeRepository cellTypeRepository) {
		this.geneService = geneService;
		this.cellTypeRepository = cellTypeRepository;
	}

	public List<AutocompleteResult> query(String searchTerm) throws IOException {
		List<MyGeneInfoHit> myGeneInfoHits = geneService.querySymbolAndAlias(searchTerm);
		List<CellType> cellTypes = cellTypeRepository.findByCellTypeContainingOrSynonymContaining(searchTerm);
		List<CellType> regions = cellTypeRepository.findByStructureRegionContaining(searchTerm);
		List<CellType> subregions = cellTypeRepository.findByStructureSubregionContaining(searchTerm);
		List<AutocompleteResult> myGeneResults = convertMyGeneInfoHitsToAutocompleteResults(myGeneInfoHits);
		List<AutocompleteResult> cellTypeResults = convertCellTypesToAutocompleteResults(cellTypes, regions,
				subregions);
		myGeneResults.addAll(cellTypeResults);
		return myGeneResults;
	}

	public List<AutocompleteResult> convertMyGeneInfoHitsToAutocompleteResults(List<MyGeneInfoHit> hits) {
		List<AutocompleteResult> autocompleteResults = new ArrayList<>();
		for (MyGeneInfoHit hit : hits) {
			autocompleteResults.add(
					new AutocompleteResult(hit.getSymbol(), hit.getName(), hit.getId(), TYPE_GENE, hit.getAlias()));
		}
		return autocompleteResults;
	}

	public List<AutocompleteResult> convertCellTypesToAutocompleteResults(List<CellType> cellTypes,
			List<CellType> regions, List<CellType> subregions) {
		List<CellType> cellTypeListNoDupes = cellTypes.stream().distinct().collect(Collectors.toList());
		List<AutocompleteResult> autocompleteResults = new ArrayList<>();
		List<Integer> idsAdded = new ArrayList<>();

		logger.warn("converting cell types to autocomplete results");

		for (CellType cellType : cellTypeListNoDupes) {
			logger.warn("added cell type: " + cellType.getCellType() + " with id: " + cellType.getCellTypeId());
			autocompleteResults.add(new AutocompleteResult(cellType.getCellType(), null,
					Integer.toString(cellType.getCellTypeId()), TYPE_CELL_TYPE, cellType.getSynonymStringList()));
			idsAdded.add(cellType.getCellTypeId());
		}

		List<String> subregionHits = new ArrayList<>();
		for (CellType subregion : subregions) {
			if (subregionHits.contains(subregion.getStructureSubregion())
					|| idsAdded.contains(subregion.getCellTypeId())) {
				logger.warn("skipped subregion: " + subregion.getStructureSubregion() + " with id: "
						+ subregion.getCellTypeId());
			} else {
				logger.warn("added subregion: " + subregion.getStructureSubregion() + " with id: "
						+ subregion.getCellTypeId());
				autocompleteResults.add(new AutocompleteResult(subregion.getStructureSubregion(), null,
						Integer.toString(subregion.getCellTypeId()), TYPE_CELL_TYPE, null));
				subregionHits.add(subregion.getStructureSubregion());
				idsAdded.add(subregion.getCellTypeId());
			}
		}

		List<String> regionHits = new ArrayList<>();
		for (CellType region : regions) {
			if (regionHits.contains(region.getStructureRegion()) || idsAdded.contains(region.getCellTypeId())) {
				logger.warn("skipped region: " + region.getStructureRegion() + " with id: " + region.getCellTypeId());
			} else {
				logger.warn("added region: " + region.getStructureRegion() + " with id: " + region.getCellTypeId());
				autocompleteResults.add(new AutocompleteResult(region.getStructureRegion(), null,
						Integer.toString(region.getCellTypeId()), TYPE_CELL_TYPE, null));
				regionHits.add(region.getStructureRegion());
				idsAdded.add(region.getCellTypeId());
			}
		}

		return autocompleteResults;
	}

}
