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

import co.id.btpn.web.containerMonitoring.model.PolicyFalco;
import co.id.btpn.web.containerMonitoring.model.RuleFalco;
import co.id.btpn.web.containerMonitoring.service.PolicyFalcoService;
import co.id.btpn.web.containerMonitoring.service.RuleFalcoService;



/**
 *
 * @author Ferry Fadly
 */
@Controller
@SessionAttributes("attributes")
public class PolicyFalcoController {

	@Autowired
	PolicyFalcoService policyFalcoService;

	@Autowired
    RuleFalcoService ruleFalcoService;
	

    @GetMapping("policyfalcoindex")
    public String index(PolicyFalco policyFalco, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
      
    	List <PolicyFalco> list= policyFalcoService.findAll();
    	model.addAttribute("list", list);
        
    	return "auth/policyfalco/index";
    }
    
    @GetMapping("policyfalcoadd")
    public String add(PolicyFalco policyFalco, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
        
        List <RuleFalco> list= ruleFalcoService.findAll();
    	model.addAttribute("list", list);
    	
    	return "auth/policyfalco/add";
    }


    @PostMapping("policyfalcoadd")
    public String addPost(PolicyFalco policyFalco, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
        
    	policyFalcoService.save(policyFalco);;
    	
    	return "redirect:policyfalcoindex";
    }
    
    
    @GetMapping("policyfalcoedit")
    public String edit(PolicyFalco policyFalco, Model model, @ModelAttribute("attributes") Map<?,?> attributes , @RequestParam Long id) {
    	
    	policyFalco = policyFalcoService.findById(id);
    	model.addAttribute("policyFalco", policyFalco);
        
        List <RuleFalco> list= ruleFalcoService.findAll();
    	model.addAttribute("list", list);
    	
    	return "auth/policyfalco/edit";
    }


    @PostMapping("policyfalcoedit")
    public String editPost(PolicyFalco policyFalco, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
        
    	policyFalcoService.update(policyFalco);
    	
    	return "redirect:policyfalcoindex";
    }
    
    
    @GetMapping("policyfalcodelete")
    public String delete(PolicyFalco policyFalco, Model model, @ModelAttribute("attributes") Map<?,?> attributes , @RequestParam Long id) {
          	
    	policyFalcoService.deleteById(id);
    	
    	return "redirect:policyfalcoindex";
    }
        
    @ModelAttribute("attributes")
    public Map<?,?> attributes() {
        return new HashMap<String,String>();
    }

}
