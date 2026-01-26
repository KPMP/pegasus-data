package org.kpmp.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.Collection;

@Controller
@Tag(name = "Cache Management", description = "API for managing application caches")
public class CacheController {

    @Autowired
    private CacheManager cacheManager;

    @Operation(summary = "Clear all caches", description = "Clears all application caches including gene expression, umap data, and other cached results")
    @ApiResponse(responseCode = "200", description = "Caches cleared successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CacheResponse.class)))
    @RequestMapping(value = "/v1/clearCache", method = RequestMethod.GET)
    public @ResponseBody CacheResponse clearCache(){
        Collection<String> cacheNames = cacheManager.getCacheNames();
        CacheResponse clearCacheResponse = new CacheResponse();
        clearCacheResponse.setMessage("Caches successfully cleared!");
        for(String name:cacheNames){
            if (cacheManager.getCache(name) != null) {
                cacheManager.getCache(name).clear();
            } else {
                clearCacheResponse.setMessage("There was a problem getting the " + name + " cache.");
                break;
            }
        }
        clearCacheResponse.setCacheNames(cacheNames);
        return clearCacheResponse;
    }
}
