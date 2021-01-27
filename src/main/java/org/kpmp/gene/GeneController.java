package org.kpmp.gene;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import org.kpmp.gene.GeneService;

@Controller
public class GeneController {

    private GeneService geneService;

    @Autowired
    public GeneController(GeneService geneService) {
        this.geneService = geneService;
    }

    @RequestMapping(value = "/v1/gene/query/{queryString}", method = RequestMethod.GET)
    public @ResponseBody
    MyGeneInfoResult queryBySymbol(@PathVariable String queryString, HttpServletRequest request)
            throws JSONException, IOException {
        return geneService.query(queryString);
    }
}
