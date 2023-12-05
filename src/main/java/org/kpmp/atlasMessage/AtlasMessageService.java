package org.kpmp.atlasMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AtlasMessageService {
    private AtlasMessageRepository atlasMessageRepo;

    @Autowired
    public AtlasMessageService(AtlasMessageRepository atlasMessageRepo){
        this.atlasMessageRepo = atlasMessageRepo;
    }

    public List<AtlasMessage> getAtlasMessage(){
        List<AtlasMessage> messages = new ArrayList<>();
        messages.addAll(atlasMessageRepo.getAtlasMessages());
        return messages;
    }

}