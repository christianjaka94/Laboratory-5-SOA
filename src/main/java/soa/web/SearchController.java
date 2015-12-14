package soa.web;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class SearchController {

	@Autowired
	  private ProducerTemplate producerTemplate;

	@RequestMapping("/")
    public String index() {
        return "index";
    }


    @RequestMapping(value="/search")
    @ResponseBody
    public Object search(@RequestParam("q") String q) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		String[] entrada = q.split(" max:");
		if(entrada.length == 2){
			parametros.put("CamelTwitterKeywords", entrada[0]);
			parametros.put("CamelTwitterCount", entrada[1]);
		}
		else{
			parametros.put("CamelTwitterKeywords", entrada[0]);
		}
        return producerTemplate.requestBodyAndHeaders("direct:search", "", parametros);
    }
}