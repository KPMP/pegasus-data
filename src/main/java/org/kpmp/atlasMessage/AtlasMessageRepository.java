package org.kpmp.atlasMessage;


import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface AtlasMessageRepository extends CrudRepository<AtlasMessage, List>{
    @Cacheable("messageByStartDate")
    @Query(value = "SELECT * FROM atlas_messages am WHERE start_date <= CURRENT_DATE() AND end_date >= CURRENT_DATE()", nativeQuery = true)
    List<AtlasMessage> getAtlasMessages();
}
