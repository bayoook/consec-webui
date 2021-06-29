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

import co.id.btpn.web.containerMonitoring.model.ConfigAnchore;
import co.id.btpn.web.containerMonitoring.service.ConfigAnchoreService;



/**
 *
 * @author Ferry Fadly
 */
@Controller
@SessionAttributes("attributes")
public class ConfigAnchoreController {

	@Autowired
	ConfigAnchoreService configAnchoreService;
	

    @GetMapping("configanchoreindex")
    public String index(ConfigAnchore configAnchore, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
      
    	List <ConfigAnchore> list= configAnchoreService.findAll();
    	model.addAttribute("list", list);
        
    	return "auth/configanchore/index";
    }
    
    @GetMapping("configanchoreadd")
    public String add(ConfigAnchore configAnchore, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
        
    	
    	return "auth/configanchore/add";
    }


    @PostMapping("configanchoreadd")
    public String addPost(ConfigAnchore configAnchore, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
        
    	configAnchoreService.save(configAnchore);;
    	
    	return "redirect:configanchoreindex";
    }
    
    
    @GetMapping("configanchoreedit")
    public String edit(ConfigAnchore configAnchore, Model model, @ModelAttribute("attributes") Map<?,?> attributes , @RequestParam Long id) {
    	
    	configAnchore = configAnchoreService.findById(id);
    	
    	model.addAttribute("configAnchore", configAnchore);
        
    	
    	return "auth/configanchore/edit";
    }


    @PostMapping("configanchoreedit")
    public String editPost(ConfigAnchore configAnchore, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
        
    	configAnchoreService.update(configAnchore);
    	
    	return "redirect:configanchoreindex";
    }
    
    
    @GetMapping("configanchoredelete")
    public String delete(ConfigAnchore configAnchore, Model model, @ModelAttribute("attributes") Map<?,?> attributes , @RequestParam Long id) {
          	
    	configAnchoreService.deleteById(id);
    	
    	return "redirect:configanchoreindex";
    }
        
    @ModelAttribute("attributes")
    public Map<?,?> attributes() {
        return new HashMap<String,String>();
    }

}
