package org.kpmp.file;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ARFileInfoRepository extends CrudRepository<ARFileInfo, Integer>{
    
    @Cacheable("totalFileCount")
	@Query(value = "select count(distinct(file_id)) from ar_file_info where release_sunset_version is null" , nativeQuery = true)
	Long getTotalFileCount();
}
