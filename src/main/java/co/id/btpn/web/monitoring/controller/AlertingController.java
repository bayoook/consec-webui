package co.id.btpn.web.monitoring.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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

import co.id.btpn.web.monitoring.model.policy.anchore.Param;
import co.id.btpn.web.monitoring.service.OpenshiftClientService;
import co.id.btpn.web.monitoring.util.Util;
import io.fabric8.kubernetes.api.model.ConfigMap;
import io.fabric8.kubernetes.api.model.ConfigMapBuilder;
import io.fabric8.kubernetes.api.model.ConfigMapList;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import co.id.btpn.web.monitoring.model.CustomRuleFalco;
import co.id.btpn.web.monitoring.model.UserLog;
import co.id.btpn.web.monitoring.repository.UserLogRepository;


/**
 *
 * @author Ferry Fadly
 */
@Controller
@SessionAttributes("attributes")
public class AlertingController {


    @Value("${falco.url}")
    private String falcoUrl;

    @Value("${falco.username}")
    private String falcoUsername;

    @Value("${falco.password}")
    private String falcoPassword;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    OpenshiftClientService openshiftClientService;

	@Autowired
	private UserLogRepository userLogRepository;

	@Autowired
	private Util util;

	String PRETY_PREFIX = "<pre class='language-yaml'><code>";
	String PRETY_SUFIX = "</code></pre>";
    String PRETY_PREFIX_ = "<pre class=\"language-yaml\"><code>";
	

	

    @GetMapping("imagealertindex")
    public String index(CustomRuleFalco customRuleFalco, Model model, @ModelAttribute("attributes") Map<?,?> attributes) throws IOException {
      
    
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
       // headers.setBasicAuth(anchoreUsername, anchorePassword);
       headers.setBasicAuth(falcoUsername, falcoPassword);

        Map<String, String> bodyParamMap = new HashMap<String, String>();

        HttpEntity requestEntity = new HttpEntity(bodyParamMap,headers);

        ResponseEntity  responseEntity =  restTemplate.exchange(falcoUrl+"/rule/action", HttpMethod.GET, requestEntity, String.class);
        CustomRuleFalco[] customRuleFalcoList = new Gson().fromJson(responseEntity.getBody().toString(), CustomRuleFalco[].class);



        ConfigMap cmcustom =  openshiftClientService.getConnection().configMaps().inNamespace("consec-dev").withName("mail-options").get();
        Properties properties = new Properties();
        InputStream stream = new ByteArrayInputStream(cmcustom.getData().get("mail-options.incl").getBytes(StandardCharsets.UTF_8));
        properties.load(stream);
 
 
        CustomRuleFalco imageScanNOTIFY = new CustomRuleFalco();

        
         imageScanNOTIFY.setId(5);
         imageScanNOTIFY.setActionName("ImageScanNOTIFY");
         imageScanNOTIFY.setRuleName("Image Scan");
         imageScanNOTIFY.setEnabled(Boolean.parseBoolean(properties.getProperty("ImageScanNOTIFY")) ? 1 : 0);
         
         
         customRuleFalcoList = addX(customRuleFalcoList.length, customRuleFalcoList , imageScanNOTIFY );

        model.addAttribute("list", customRuleFalcoList);


        responseEntity =  restTemplate.exchange(falcoUrl+"/action", HttpMethod.GET, requestEntity, String.class);
        
     
        model.addAttribute("action",(String)responseEntity.getBody() );

        
    	return "auth/alerting/image";
    }
    


    @PostMapping("imagealertupdate")
    public  @ResponseBody String updateCustomRule( @RequestParam Map<String,String> allParams ) {

    	String enabled = "";
    	String actionId = "";
    	String id = "";

        if (allParams.containsKey("id")){
    		id =  allParams.get("id");
    	}

    	if (allParams.containsKey("actionId")){
    		actionId =  allParams.get("actionId");
    	}

    	if (allParams.containsKey("enabled")){
    		enabled =  allParams.get("enabled");
    	}


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.setBasicAuth(falcoUsername, falcoPassword);

        Map<String, String> bodyParamMap = new HashMap<String, String>();
        bodyParamMap.put("action_id", actionId );
        bodyParamMap.put("enabled", enabled);

        HttpEntity requestEntity = new HttpEntity(bodyParamMap,headers);

        ResponseEntity<String> responseEntity =  restTemplate.exchange(falcoUrl+"/rule/action/"+id, HttpMethod.PUT, requestEntity, String.class);
      

		UserLog userLog = new UserLog();
		userLog.setActivity("Update Alert ID = \""+ id +"\", actionId =  \""+ actionId +"\", enabled =  \""+ enabled +"\"  ");
		userLog.setLogDate(new Date());
		userLog.setName(util.getLoggedUserName());
		userLogRepository.save(userLog);

    	return responseEntity.getBody();
    }

    
    @GetMapping("runtimealertindex")
    public String runtimeIndex(CustomRuleFalco customRuleFalco, Model model, @ModelAttribute("attributes") Map<?,?> attributes) throws IOException {
      
       ConfigMap cmcustom =  openshiftClientService.getConnection().configMaps().inNamespace("consec-dev").withName("mail-options").get();
       Properties properties = new Properties();
       InputStream stream = new ByteArrayInputStream(cmcustom.getData().get("mail-options.incl").getBytes(StandardCharsets.UTF_8));
       properties.load(stream);

       List <Param> listObj =  new ArrayList <>();

        Param falcoNOTIFY = new Param();
        Param falcoACTION = new Param();
        Param imageScanNOTIFY = new Param();
       
        falcoNOTIFY.setDesc("Falco Notify Alert");
        falcoNOTIFY.setName("FalcoNOTIFY");
        falcoNOTIFY.setValue(properties.getProperty("FalcoNOTIFY"));
        falcoACTION.setDesc("Falco Action Alert");
        falcoACTION.setName("FalcoACTION");
        falcoACTION.setValue(properties.getProperty("FalcoACTION"));
        imageScanNOTIFY.setDesc("Image Scan Notify Alert");
        imageScanNOTIFY.setName("ImageScanNOTIFY");
        imageScanNOTIFY.setValue(properties.getProperty("ImageScanNOTIFY"));

        listObj.add(falcoNOTIFY);
        listObj.add(falcoACTION);
        listObj.add(imageScanNOTIFY);

        model.addAttribute("list", listObj);

        
    	return "auth/alerting/runtime";
    }




    @PostMapping("runtimealertupdate")
    public  @ResponseBody String updateCustomRule2( @RequestParam Map<String,String> allParams ) throws IOException {

    	String enabled = "";
    	String name = "";


    	if (allParams.containsKey("name")){
    		name =  allParams.get("name");
    	}

    	if (allParams.containsKey("enabled")){
    		enabled =  allParams.get("enabled");
    	}


        ConfigMap cmcustom =  openshiftClientService.getConnection().configMaps().inNamespace("consec-dev").withName("mail-options").get();
        Properties properties = new Properties();
        InputStream stream = new ByteArrayInputStream(cmcustom.getData().get("mail-options.incl").getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        
        properties.load(stream);
        properties.setProperty(name, enabled);
        properties.store(byteArrayOutputStream, "");

        String out = new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8);


        Map <String,String> configMapData = new HashMap<String,String>();
        configMapData.put("mail-options.incl", out);

        ConfigMap newConfigMap = new ConfigMapBuilder().withNewMetadata()
            .withName("mail-options")
            .withNamespace("consec-dev")
            .addToLabels("app", "falco")
            .endMetadata()
            .addToData(configMapData)
            .build();
    
        openshiftClientService.getConnection().configMaps().inNamespace("consec-dev").createOrReplace(newConfigMap);

        UserLog userLog = new UserLog();
		userLog.setActivity("Update Runtime Alert = "+ enabled);
		userLog.setLogDate(new Date());
		userLog.setName(util.getLoggedUserName());
		userLogRepository.save(userLog);

    	return "OK";
    }


    public  CustomRuleFalco[] addX(int n, CustomRuleFalco arr[], CustomRuleFalco x)
    {
        int i;
  
        // create a new array of size n+1
        CustomRuleFalco newarr[] = new CustomRuleFalco[n + 1];
  
        for (i = 0; i < n; i++)
            newarr[i] = arr[i];
  
        newarr[n] = x;
  
        return newarr;
    }



    @GetMapping("emailconfig")
    public String edit(Param  paramFalco, Model model, @ModelAttribute("attributes") Map<?,?> attributes , @RequestParam String id) {
    	

       ConfigMap cm =  openshiftClientService.getConnection().configMaps().inNamespace("consec-dev").withName("mail-recipient-list").get();
       Map<String,String> map = cm.getData();

       paramFalco.setName(id);
       paramFalco.setValue(PRETY_PREFIX+map.get(id)+PRETY_SUFIX);
        

        model.addAttribute("configFalco", paramFalco);
        
    	
    	return "auth/alerting/edit";
    }


    @PostMapping("emailconfig")
    public String editPost(Param  paramFalco, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
        
       
        NonNamespaceOperation<ConfigMap, ConfigMapList, Resource<ConfigMap>>  cm =  openshiftClientService.getConnection().configMaps().inNamespace("consec-dev");
       Map<String,String> map = cm.withName("mail-recipient-list").get().getData();


    
       map.put(paramFalco.getName(), paramFalco.getValue().replace(PRETY_PREFIX, "").replace(PRETY_SUFIX, "").replace(PRETY_PREFIX_, ""));
   
       ConfigMap newConfigMap = new ConfigMapBuilder().withNewMetadata()
       .withName("mail-recipient-list")
       .withNamespace("consec-dev")
       .endMetadata()
       .addToData(map)
       .build();

       
       cm.createOrReplace(newConfigMap);

        UserLog userLog = new UserLog();
		userLog.setActivity("Update Mail Recipient Config = "+ paramFalco.getValue().replace(PRETY_PREFIX, "").replace(PRETY_SUFIX, "").replace(PRETY_PREFIX_, ""));
		userLog.setLogDate(new Date());
		userLog.setName(util.getLoggedUserName());
		userLogRepository.save(userLog);

    	return "redirect:imagealertindex";
    }
    
    
        
    @ModelAttribute("attributes")
    public Map<?,?> attributes() {
        return new HashMap<String,String>();
    }

}
