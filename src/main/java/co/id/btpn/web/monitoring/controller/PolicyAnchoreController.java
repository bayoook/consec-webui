package co.id.btpn.web.monitoring.controller;

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

import co.id.btpn.web.monitoring.model.PolicyAnchore;
import co.id.btpn.web.monitoring.service.PolicyAnchoreService;



/**
 *
 * @author Ferry Fadly
 */
@Controller
@SessionAttributes("attributes")
public class PolicyAnchoreController {

	@Autowired
	PolicyAnchoreService policyAnchoreService;
	

    @GetMapping("policyanchoreindex")
    public String index(PolicyAnchore policyAnchore, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
      
    	List <PolicyAnchore> list= policyAnchoreService.findAll();
    	model.addAttribute("list", list);
        
    	return "auth/policyanchore/index";
    }
    
    @GetMapping("policyanchoreadd")
    public String add(PolicyAnchore policyAnchore, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
        
    	
    	return "auth/policyanchore/add";
    }


    @PostMapping("policyanchoreadd")
    public String addPost(PolicyAnchore policyAnchore, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
        
    	policyAnchoreService.save(policyAnchore);;
    	
    	return "redirect:policyanchoreindex";
    }
    
    
    @GetMapping("policyanchoreedit")
    public String edit(PolicyAnchore policyAnchore, Model model, @ModelAttribute("attributes") Map<?,?> attributes , @RequestParam Long id) {
    	
    	policyAnchore = policyAnchoreService.findById(id);
       
    	model.addAttribute("policyAnchore", policyAnchore);
        
    	
    	return "auth/policyanchore/edit";
    }


    @PostMapping("policyanchoreedit")
    public String editPost(PolicyAnchore policyAnchore, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
        
    	policyAnchoreService.update(policyAnchore);
    	
    	return "redirect:policyanchoreindex";
    }
    
    
    @GetMapping("policyanchoredelete")
    public String delete(PolicyAnchore policyAnchore, Model model, @ModelAttribute("attributes") Map<?,?> attributes , @RequestParam Long id) {
          	
    	policyAnchoreService.deleteById(id);
    	
    	return "redirect:policyanchoreindex";
    }
        
    @ModelAttribute("attributes")
    public Map<?,?> attributes() {
        return new HashMap<String,String>();
    }

}
