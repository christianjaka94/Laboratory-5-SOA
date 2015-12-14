package soa.web;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


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
	//We add a parameter max , which contains,if it true, the number of tweets
    public Object search(@RequestParam("q") String q, @RequestParam(value="n", required=false) Integer n) {
		HashMap<String,Object> hashmap = new HashMap<String, Object>();
		hashmap.put("CamelTwitterKeywords",q);
		if(n!=null){
			hashmap.put("CamelTwitterCount",n);
		}
        return producerTemplate.requestBodyAndHeader("direct:search", "", "CamelTwitterKeywords", q);
    }
}