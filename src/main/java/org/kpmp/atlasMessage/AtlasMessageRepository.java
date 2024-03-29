package org.kpmp.atlasMessage;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface AtlasMessageRepository extends CrudRepository<AtlasMessage, List<AtlasMessage>>{
    @Query(value = "SELECT * FROM atlas_messages am WHERE start_date <= CURRENT_DATE() AND end_date >= CURRENT_DATE() ORDER BY start_date ASC", nativeQuery = true)
    List<AtlasMessage> getAtlasMessages();
}
