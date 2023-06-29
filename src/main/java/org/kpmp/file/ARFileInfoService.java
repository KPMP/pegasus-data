package org.kpmp.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ARFileInfoService {
    
    private ARFileInfoRepository repository;

    @Autowired
    public ARFileInfoService (ARFileInfoRepository repository) {
        this.repository = repository;
    }

    public Long getRepositoryTotalFileCount() {
        return repository.getTotalFileCount();
    }

}
