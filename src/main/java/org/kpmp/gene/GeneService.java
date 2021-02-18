package org.kpmp.gene;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

@Service
public class GeneService {

    public static final String GET_MY_GENE_INFO_QUERY_SYMBOL = "http://mygene.info/v3/query?q=symbol:%s%%2A&species=9606&fields=symbol,name,taxid,entrezgene,alias&size=100";
    public static final String GET_MY_GENE_INFO_QUERY_ALIAS = "http://mygene.info/v3/query?q=alias:%s%%2A%%20NOT%%20symbol:%s%%2A&species=9606&fields=symbol,name,taxid,entrezgene,alias&size=100";

    private ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public MyGeneInfoResult query(String url, String queryString) throws IOException {
            String encodedQueryString = URLEncoder.encode(queryString.replaceAll("[/|\\(|\\)]",""), StandardCharsets.UTF_8.toString());
            URL queryUrl = new URL(String.format(url, encodedQueryString, encodedQueryString));
            HttpURLConnection con = (HttpURLConnection) queryUrl.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer json = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
            con.disconnect();
            return mapper.readValue(json.toString(), MyGeneInfoResult.class);
    }

    public List<MyGeneInfoHit> querySymbolAndAlias(String queryString) throws IOException {
        List<MyGeneInfoHit> symbolResults = query(GET_MY_GENE_INFO_QUERY_SYMBOL, queryString).getHits();
        List<MyGeneInfoHit> aliasResults = query(GET_MY_GENE_INFO_QUERY_ALIAS, queryString).getHits();
        List<MyGeneInfoHit> finalResults = new ArrayList<>();
        finalResults.addAll(sortAndSlice(symbolResults, 20));
        finalResults.addAll(sortAndSlice(aliasResults, 20));
        return finalResults.size() <= 20 ? finalResults : finalResults.subList(0, 20);
    }

    public List<MyGeneInfoHit> sortAndSlice(List<MyGeneInfoHit> hits, int max) {
        List<MyGeneInfoHit> slicedHits = hits.size() <= max ? hits : hits.subList(0, max);
        Collections.sort(slicedHits);
        return slicedHits;
    }

}
