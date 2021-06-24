package org.kpmp.geneExpressionSummary;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RTSummaryRepository extends CrudRepository<RTSummaryValue, String> {

    @Query(value = "SELECT 'all_segments' as segment, MAX(all_count) as all_count, MAX(hrt_count) as hrt_count, MAX(aki_count) as aki_count, MAX(ckd_count) as ckd_count " +
            "FROM rt_summary WHERE segment != 'Bulk'", nativeQuery = true)
    RTSummaryValue getCountByTissue();

}
