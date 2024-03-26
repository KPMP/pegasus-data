package org.kpmp.atlasMessage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AtlasMessageService {
    private AtlasMessageRepository atlasMessageRepo;

    @Autowired
    public AtlasMessageService(AtlasMessageRepository atlasMessageRepo){
        this.atlasMessageRepo = atlasMessageRepo;
    }

    public List<AtlasMessage> getAtlasMessage(){
        return atlasMessageRepo.getAtlasMessages();
    }

}