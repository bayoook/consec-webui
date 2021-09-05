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
import co.id.btpn.web.monitoring.model.image.Image;
import co.id.btpn.web.monitoring.model.image.Registry;



/**
 *
 * @author Ferry Fadly
 */
@Controller
@SessionAttributes("attributes")
public class RegistryController {


    
    @Value("${anchore.url}")
    private String anchoreUrl;

    @Value("${anchore.username}")
    private String anchoreUsername;

    @Value("${anchore.password}")
    private String anchorePassword;

    @Autowired
    private RestTemplate restTemplate;
	

    
   
    @GetMapping("scanregistryindex") 
    public String scanRegistry(Model model, @ModelAttribute("attributes") Map<?,?> attributes) { 
         
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(anchoreUsername, anchorePassword);

        Map<String, String> bodyParamMap = new HashMap<String, String>();

        HttpEntity requestEntity = new HttpEntity(bodyParamMap,headers);
        
        ResponseEntity<String> responseEntity =  restTemplate.exchange(anchoreUrl+"/registries", HttpMethod.GET, requestEntity, String.class);
    
        Registry[] registryList = new Gson().fromJson(responseEntity.getBody().toString(), Registry[].class);

        model.addAttribute("list", registryList);
        
        return "auth/scanregistry/index"; 
    }


    @GetMapping("scanregistryadd")
    public String add(Registry registry, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
        
    	
    	return "auth/scanregistry/add";
    }


    @PostMapping("scanregistryadd")
    public String addPost(Registry registry, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
        
    	
        HttpHeaders headers = new HttpHeaders();
        
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(anchoreUsername, anchorePassword);


        Gson gson = new Gson();
        String json = gson.toJson(registry);

        HttpEntity requestEntity = new HttpEntity(json,headers);
        ResponseEntity<String> responseEntity =  restTemplate.exchange(anchoreUrl+"/registries", HttpMethod.POST, requestEntity, String.class);
              
    	return "redirect:scanregistryindex";
    }


    @GetMapping("scanregistryedit")
    public String edit(Registry registry, Model model, @ModelAttribute("attributes") Map<?,?> attributes, @RequestParam String rname) {
        

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(anchoreUsername, anchorePassword);

        Map<String, String> bodyParamMap = new HashMap<String, String>();

        HttpEntity requestEntity = new HttpEntity(bodyParamMap,headers);
        
        ResponseEntity<String> responseEntity =  restTemplate.exchange(anchoreUrl+"/registries/"+rname, HttpMethod.GET, requestEntity, String.class);

        Registry[] imageList = new Gson().fromJson(responseEntity.getBody().toString(), Registry[].class);

        model.addAttribute("registry", imageList[0]); 

    	
    	return "auth/scanregistry/edit";
    }

    @PostMapping("scanregistryedit")
    public String addEditPost(Registry registry, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
        
    	
        HttpHeaders headers = new HttpHeaders();
        
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(anchoreUsername, anchorePassword);


        Gson gson = new Gson();
        String json = gson.toJson(registry);

        HttpEntity requestEntity = new HttpEntity(json,headers);
        ResponseEntity<String> responseEntity =  restTemplate.exchange(anchoreUrl+"/registries/"+registry.getRegistry(), HttpMethod.PUT, requestEntity, String.class);
              
    	return "redirect:scanregistryindex";
    }


    @GetMapping("scanregistrydelete")
    public String delete(Registry registry, Model model, @ModelAttribute("attributes") Map<?,?> attributes , @RequestParam String rname) {
          	

        HttpHeaders headers = new HttpHeaders();
        
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(anchoreUsername, anchorePassword);

        registry = new Registry();
        registry.setRegistry(rname);

        Gson gson = new Gson();
        String json = gson.toJson(registry);

        HttpEntity requestEntity = new HttpEntity(json,headers);
        ResponseEntity<String> responseEntity =  restTemplate.exchange(anchoreUrl+"/registries/"+registry.getRegistry(), HttpMethod.DELETE, requestEntity, String.class);
              
    	
    	
    	return "redirect:scanregistryindex";
    }
    
    
    
    
        
    @ModelAttribute("attributes")
    public Map<?,?> attributes() {
        return new HashMap<String,String>();
    }

}
