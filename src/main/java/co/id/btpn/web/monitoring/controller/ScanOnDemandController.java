package co.id.btpn.web.monitoring.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import co.id.btpn.web.monitoring.model.CustomRuleActionFalco;
import co.id.btpn.web.monitoring.model.CustomRuleFalco;
import co.id.btpn.web.monitoring.model.image.Annotations;
import co.id.btpn.web.monitoring.model.image.Image;
import co.id.btpn.web.monitoring.model.image.ImagePostScan;



/**
 *
 * @author Ferry Fadly
 */
@Controller
@SessionAttributes("attributes")
public class ScanOnDemandController {


    
    @Value("${anchore.url}")
    private String anchoreUrl;

    @Value("${anchore.username}")
    private String anchoreUsername;

    @Value("${anchore.password}")
    private String anchorePassword;

    @Autowired
    private RestTemplate restTemplate;
	

    
    @GetMapping("scanondemandindex") public String scanOnDemand(Model model, @ModelAttribute("attributes") Map<?,?> attributes) { 
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(anchoreUsername, anchorePassword);

        Map<String, String> bodyParamMap = new HashMap<String, String>();

        HttpEntity requestEntity = new HttpEntity(bodyParamMap,headers);
        
        ResponseEntity<String> responseEntity =  restTemplate.exchange(anchoreUrl+"/images", HttpMethod.GET, requestEntity, String.class);
    
        Image[] imageList = new Gson().fromJson(responseEntity.getBody().toString(), Image[].class);

        model.addAttribute("list", imageList);

        return "auth/scanondemand/index"; 
    }

    @PostMapping("scanondemandadd")
    public  @ResponseBody String updateCustomRule( @RequestParam Map<String,String> allParams ) {

    	String tag = "";

    	Annotations annotations = new Annotations();
        annotations.setOrigins("webui");

        if (allParams.containsKey("tag")){
    		tag =  allParams.get("tag");
    	}

        ImagePostScan post = new ImagePostScan();
        post.setTag(tag);
        post.setAnnotations(annotations);


        HttpHeaders headers = new HttpHeaders();
        
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(anchoreUsername, anchorePassword);

        HttpEntity requestEntity = new HttpEntity(post,headers);

        ResponseEntity<String> responseEntity =  restTemplate.exchange(anchoreUrl+"/images", HttpMethod.POST, requestEntity, String.class);
      
    	return responseEntity.getBody();
    }

    
    
    
        
    @ModelAttribute("attributes")
    public Map<?,?> attributes() {
        return new HashMap<String,String>();
    }

}
