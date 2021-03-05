package org.kpmp.cellType;

import java.util.List;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

@Component
public class CellTypeStructureRegionResolver implements GraphQLResolver<CellTypeStructureRegion> {
	
	public List<CellTypeStructureSubregion> getCellTypeSubregions(CellTypeStructureRegion region) {
		return region.getCellTypeSubregions();
	}

}
