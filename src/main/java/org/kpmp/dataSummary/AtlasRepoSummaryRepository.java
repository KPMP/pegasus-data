package org.kpmp.dataSummary;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

public interface AtlasRepoSummaryRepository extends CrudRepository<ExperimentalStrategyValue, ExperimentalStrategyId> {

	@Override
	@Cacheable("repoSummaryCounts")
	List<ExperimentalStrategyValue> findAll();

}
