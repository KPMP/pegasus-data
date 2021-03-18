package org.kpmp.umap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UmapDataService {

	private UmapPointRepository umapPointRepo;

	@Autowired
	public UmapDataService(UmapPointRepository umapPointRepo) {
		this.umapPointRepo = umapPointRepo;
	}

	public List<UmapPoint> getUmapPoints(String dataType) {
		return umapPointRepo.findByDataType(dataType);
	}

}
