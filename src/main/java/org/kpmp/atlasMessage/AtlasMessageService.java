package org.kpmp.atlasMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;


@Service
public class AtlasMessageService {
    private AtlasMessage atlasMessage;
    private AtlasMessageRepository atlasMessageRepo;

    @Autowired
    public AtlasMessageService(AtlasMessageRepository atlasMessageRepo){
        this.atlasMessageRepo = atlasMessageRepo;
    }

    public List<AtlasMessage> getAtlasMessage(){
        LocalDate localDate = LocalDate.now();
        ZoneId defaultZoneId = ZoneId.systemDefault();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Date currentDate = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        List<AtlasMessage> messages = new ArrayList<>();
        Map<Integer, AtlasMessage> messageMap = new HashMap<>();
        messages.addAll(atlasMessageRepo.getAtlasMessages());
        for (AtlasMessage atlasmessage : messages){
            if(currentDate.compareTo(atlasmessage.getStartDate()) >= 0 && currentDate.compareTo(atlasmessage.getEndDate()) <= 0){
                messageMap.put(atlasmessage.getAtlasMessageId(), atlasmessage);
            }
        }
        return new ArrayList<>(messageMap.values());
        
    }

}