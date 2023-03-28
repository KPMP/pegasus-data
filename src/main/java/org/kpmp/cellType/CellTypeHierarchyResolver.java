package org.kpmp.cellType;

import java.util.List;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

@Component
public class CellTypeHierarchyResolver implements GraphQLResolver<CellTypeHierarchy> {

	public List<CellTypeStructureRegion> getCellTypeRegions(CellTypeHierarchy hierarchy) {
		return hierarchy.getCellTypeRegions();
	}
	
}
