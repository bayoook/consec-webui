package co.id.btpn.web.containerMonitoring.controller;

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

import co.id.btpn.web.containerMonitoring.model.ConfigFalco;
import co.id.btpn.web.containerMonitoring.service.ConfigFalcoService;



/**
 *
 * @author Ferry Fadly
 */
@Controller
@SessionAttributes("attributes")
public class ConfigFalcoController {

	@Autowired
	ConfigFalcoService configFalcoService;
	

    @GetMapping("configfalcoindex")
    public String index(ConfigFalco  configFalco, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
      
    	List <ConfigFalco> list= configFalcoService.findAll();
    	model.addAttribute("list", list);
        
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
    public String edit(ConfigFalco  configFalco, Model model, @ModelAttribute("attributes") Map<?,?> attributes , @RequestParam Long id) {
    	
    	configFalco = configFalcoService.findById(id);
    	
    	model.addAttribute("configFalco", configFalco);
        
    	
    	return "auth/configfalco/edit";
    }


    @PostMapping("configfalcoedit")
    public String editPost(ConfigFalco  configFalco, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
        
       
    	configFalcoService.update(configFalco);
    	
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
