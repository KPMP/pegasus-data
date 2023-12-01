package org.kpmp.atlasMessage;


import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface AtlasMessageRepository extends CrudRepository<AtlasMessage, List>{
    @Cacheable("messageByStartDate")
    @Query(value = "SELECT message, start_date, end_date FROM atlas_messages ORDER BY start_date ASC", nativeQuery = true)
    List<AtlasMessage> getAtlasMessages();
}
