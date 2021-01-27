package org.kpmp.gene;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

@Service
public class GeneService {

    private final String getMyGeneInfoQuery= "https://mygene.info/v3/query?q=%s&species=9606&fields=symbol,name,taxid,entrezgene,alias";
    private ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public MyGeneInfoResult query(String queryString) throws IOException {
            URL url = new URL(String.format(getMyGeneInfoQuery, queryString));
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
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
}
