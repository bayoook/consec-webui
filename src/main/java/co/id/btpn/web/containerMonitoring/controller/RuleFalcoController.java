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

import co.id.btpn.web.containerMonitoring.model.RuleFalco;
import co.id.btpn.web.containerMonitoring.service.RuleFalcoService;



/**
 *
 * @author Ferry Fadly
 */
@Controller
@SessionAttributes("attributes")
public class RuleFalcoController {

	@Autowired
	RuleFalcoService ruleFalcoService;
	

    @GetMapping("rulefalcoindex")
    public String index(RuleFalco ruleFalco, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
      
    	List <RuleFalco> list= ruleFalcoService.findAll();
    	model.addAttribute("list", list);
        
    	return "auth/rulefalco/index";
    }
    
    @GetMapping("rulefalcoadd")
    public String add(RuleFalco ruleFalco, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
        
    	
    	return "auth/rulefalco/add";
    }


    @PostMapping("rulefalcoadd")
    public String addPost(RuleFalco ruleFalco, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
        
    	ruleFalcoService.save(ruleFalco);;
    	
    	return "redirect:rulefalcoindex";
    }
    
    
    @GetMapping("rulefalcoedit")
    public String edit(RuleFalco ruleFalco, Model model, @ModelAttribute("attributes") Map<?,?> attributes , @RequestParam Long id) {
    	
    	ruleFalco = ruleFalcoService.findById(id);
       
    	model.addAttribute("ruleFalco", ruleFalco);
        
    	
    	return "auth/rulefalco/edit";
    }


    @PostMapping("rulefalcoedit")
    public String editPost(RuleFalco ruleFalco, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
        
    	ruleFalcoService.update(ruleFalco);
    	
    	return "redirect:rulefalcoindex";
    }
    
    
    @GetMapping("rulefalcodelete")
    public String delete(RuleFalco ruleFalco, Model model, @ModelAttribute("attributes") Map<?,?> attributes , @RequestParam Long id) {
          	
    	ruleFalcoService.deleteById(id);
    	
    	return "redirect:rulefalcoindex";
    }
        
    @ModelAttribute("attributes")
    public Map<?,?> attributes() {
        return new HashMap<String,String>();
    }

}
