package co.id.btpn.web.monitoring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import co.id.btpn.web.monitoring.model.ConfigFalco;
import co.id.btpn.web.monitoring.model.policy.anchore.Param;
import co.id.btpn.web.monitoring.service.ConfigFalcoService;
import co.id.btpn.web.monitoring.service.OpenshiftClientService;
import io.fabric8.kubernetes.api.model.ConfigMap;
import io.fabric8.kubernetes.api.model.ConfigMapBuilder;
import io.fabric8.kubernetes.api.model.ConfigMapList;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.Resource;



/**
 *
 * @author Ferry Fadly
 */
@Controller
@SessionAttributes("attributes")
public class ConfigFalcoController {

	@Autowired
	ConfigFalcoService configFalcoService;

    @Autowired
    OpenshiftClientService openshiftClientService;

	String PRETY_PREFIX = "<pre class='language-yaml'><code>";
	String PRETY_SUFIX = "</code></pre>";
    String PRETY_PREFIX_ = "<pre class=\"language-yaml\"><code>";
	

    @GetMapping("configfalcoindex")
    public String index(ConfigFalco  configFalco, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
      
    	// List <ConfigFalco> list= configFalcoService.findAll();
    	// model.addAttribute("list", list);
        

       ConfigMap cm =  openshiftClientService.getConnection().configMaps().inNamespace("consec-dev").withName("falco-duplicate").get();
       Map<String,String> map = cm.getData();
    

       List <Param> listObj =  new ArrayList <>();

        for (Map.Entry<String, String> entry : map.entrySet()){
            Param temp = new Param();
            temp.setName(entry.getKey());
            temp.setValue(entry.getValue());
            listObj.add(temp);
        }

        model.addAttribute("list", listObj);

    	return "auth/configfalco/index";
    }
    
    @GetMapping("configfalcoadd")
    public String add(ConfigFalco  configFalco, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
        
    	
    	return "auth/configfalco/add";
    }


    @PostMapping("configfalcoadd")
    public String addPost(ConfigFalco  configFalco, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
        
    	configFalcoService.save(configFalco);;
    	
    	return "redirect:configfalcoindex";
    }
    
    
    @GetMapping("configfalcoedit")
    public String edit(Param  paramFalco, Model model, @ModelAttribute("attributes") Map<?,?> attributes , @RequestParam String id) {
    	
    	// configFalco = configFalcoService.findById(id);
    	
    	// model.addAttribute("configFalco", configFalco);


       ConfigMap cm =  openshiftClientService.getConnection().configMaps().inNamespace("consec-dev").withName("falco-duplicate").get();
       Map<String,String> map = cm.getData();

       paramFalco.setName(id);
       paramFalco.setValue(PRETY_PREFIX+map.get(id)+PRETY_SUFIX);
        

        model.addAttribute("configFalco", paramFalco);
        
    	
    	return "auth/configfalco/edit";
    }


    @PostMapping("configfalcoedit")
    public String editPost(Param  paramFalco, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
        
       
    	//configFalcoService.update(configFalco);


        NonNamespaceOperation<ConfigMap, ConfigMapList, Resource<ConfigMap>>  cm =  openshiftClientService.getConnection().configMaps().inNamespace("consec-dev");
       Map<String,String> map = cm.withName("falco-duplicate").get().getData();

       System.out.println("after replace "+paramFalco.getValue().replace(PRETY_PREFIX, "").replace(PRETY_SUFIX, "").replace(PRETY_PREFIX_, ""));

    
       map.put(paramFalco.getName(), paramFalco.getValue().replace(PRETY_PREFIX, "").replace(PRETY_SUFIX, "").replace(PRETY_PREFIX_, ""));
   
       ConfigMap newConfigMap = new ConfigMapBuilder().withNewMetadata()
       .withName("falco-duplicate")
       .withNamespace("consec-dev")
       .addToLabels("app", "falco")
       .endMetadata()
       .addToData(map)
       .build();

       
       cm.createOrReplace(newConfigMap);

    	return "redirect:configfalcoindex";
    }
    
    
    @GetMapping("configfalcodelete")
    public String delete(ConfigFalco  configFalco, Model model, @ModelAttribute("attributes") Map<?,?> attributes , @RequestParam Long id) {
          	
    	configFalcoService.deleteById(id);
    	
    	return "redirect:configfalcoindex";
    }
        
    @ModelAttribute("attributes")
    public Map<?,?> attributes() {
        return new HashMap<String,String>();
    }

}
