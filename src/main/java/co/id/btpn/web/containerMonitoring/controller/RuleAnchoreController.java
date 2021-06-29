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

import co.id.btpn.web.containerMonitoring.model.RuleAnchore;
import co.id.btpn.web.containerMonitoring.service.RuleAnchoreService;



/**
 *
 * @author Ferry Fadly
 */
@Controller
@SessionAttributes("attributes")
public class RuleAnchoreController {

	@Autowired
	RuleAnchoreService ruleAnchoreService;
	

    @GetMapping("ruleanchoreindex")
    public String index(RuleAnchore ruleAnchore, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
      
    	List <RuleAnchore> list= ruleAnchoreService.findAll();
    	model.addAttribute("list", list);
        
    	return "auth/ruleanchore/index";
    }
    
    @GetMapping("ruleanchoreadd")
    public String add(RuleAnchore ruleAnchore, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
        
    	
    	return "auth/ruleanchore/add";
    }


    @PostMapping("ruleanchoreadd")
    public String addPost(RuleAnchore ruleAnchore, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
        
    	ruleAnchoreService.save(ruleAnchore);;
    	
    	return "redirect:ruleanchoreindex";
    }
    
    
    @GetMapping("ruleanchoreedit")
    public String edit(RuleAnchore ruleAnchore, Model model, @ModelAttribute("attributes") Map<?,?> attributes , @RequestParam Long id) {
    	
    	ruleAnchore = ruleAnchoreService.findById(id);
       
    	model.addAttribute("ruleAnchore", ruleAnchore);
        
    	
    	return "auth/ruleanchore/edit";
    }


    @PostMapping("ruleanchoreedit")
    public String editPost(RuleAnchore ruleAnchore, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
        
    	ruleAnchoreService.update(ruleAnchore);
    	
    	return "redirect:ruleanchoreindex";
    }
    
    
    @GetMapping("ruleanchoredelete")
    public String delete(RuleAnchore ruleAnchore, Model model, @ModelAttribute("attributes") Map<?,?> attributes , @RequestParam Long id) {
          	
    	ruleAnchoreService.deleteById(id);
    	
    	return "redirect:ruleanchoreindex";
    }
        
    @ModelAttribute("attributes")
    public Map<?,?> attributes() {
        return new HashMap<String,String>();
    }

}
