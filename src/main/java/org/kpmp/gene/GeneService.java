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
import java.util.List;

import org.kpmp.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GeneService {

	private Logger logger = LoggerFactory.getLogger(Query.class);
	static final String GET_MY_GENE_INFO_QUERY_SYMBOL = "http://mygene.info/v3/query?q=symbol:%s%%2A&species=9606&fields=symbol,name,taxid,entrezgene,alias&size=100";
	static final String GET_MY_GENE_INFO_QUERY_ALIAS = "http://mygene.info/v3/query?q=alias:%s%%2A%%20NOT%%20symbol:%s%%2A&species=9606&fields=symbol,name,taxid,entrezgene,alias&size=100";

	private ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
			false);

	MyGeneInfoResult query(String url, String queryString) throws IOException, Exception {
		String encodedQueryString = URLEncoder.encode(queryString.replaceAll("[/|\\(|\\)]", ""),
				StandardCharsets.UTF_8.toString());
		URL queryUrl = new URL(String.format(url, encodedQueryString, encodedQueryString));
		HttpURLConnection con = getUrlConnection(queryUrl);
		con.setRequestMethod("GET");

		switch (con.getResponseCode()) {
		case HttpURLConnection.HTTP_OK:
			break;
		case HttpURLConnection.HTTP_BAD_REQUEST:
			logger.error("Received following response from mygene.info, returning empty list of results: "
					+ con.getResponseMessage());
			con.disconnect();
			return new MyGeneInfoResult();
		default:
			con.disconnect();
			throw new Exception("Unable to connect to mygene.info: " + con.getResponseCode());

		}

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer json = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			json.append(inputLine);
		}
		in.close();
		con.disconnect();
		return mapper.readValue(json.toString(), MyGeneInfoResult.class);
	}

	HttpURLConnection getUrlConnection(URL queryUrl) throws IOException {
		return (HttpURLConnection) queryUrl.openConnection();
	}

	public List<MyGeneInfoHit> querySymbolAndAlias(String queryString) throws IOException, Exception {
		List<MyGeneInfoHit> symbolResults = query(GET_MY_GENE_INFO_QUERY_SYMBOL, queryString).getHits();
		List<MyGeneInfoHit> aliasResults = query(GET_MY_GENE_INFO_QUERY_ALIAS, queryString).getHits();
		List<MyGeneInfoHit> finalResults = new ArrayList<>();
		finalResults.addAll(sortAndSlice(symbolResults, 10));
		finalResults.addAll(sortAndSlice(aliasResults, 10));
		return finalResults.size() <= 10 ? finalResults : finalResults.subList(0, 10);
	}

	private List<MyGeneInfoHit> sortAndSlice(List<MyGeneInfoHit> hits, int max) {
		if (hits == null || hits.size() == 0) {
			return new ArrayList<MyGeneInfoHit>();
		}
		Collections.sort(hits);
		return hits.size() <= max ? hits : hits.subList(0, max);
	}

}
