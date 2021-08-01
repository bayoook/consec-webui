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

import co.id.btpn.web.monitoring.model.MasterFalcoCondition;
import co.id.btpn.web.monitoring.service.MasterFalcoConditionService;



/**
 *
 * @author Ferry Fadly
 */
@Controller
@SessionAttributes("attributes")
public class MasterFalcoConditionController {

	@Autowired
	MasterFalcoConditionService masterFalcoConditionService;
	

    @GetMapping("masterfalcoconditionindex")
    public String index(MasterFalcoCondition masterFalcoCondition, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
      
    	List <MasterFalcoCondition> list= masterFalcoConditionService.findAll();
    	model.addAttribute("list", list);
        
    	return "auth/conditionfalco/index";
    }
    
    @GetMapping("masterfalcoconditionadd")
    public String add(MasterFalcoCondition masterFalcoCondition, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
        
    	
    	return "auth/conditionfalco/add";
    }


    @PostMapping("masterfalcoconditionadd")
    public String addPost(MasterFalcoCondition masterFalcoCondition, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
        
    	masterFalcoConditionService.save(masterFalcoCondition);
    	
    	return "redirect:masterfalcoconditionindex";
    }
    
    
    @GetMapping("masterfalcoconditionedit")
    public String edit(MasterFalcoCondition masterFalcoCondition, Model model, @ModelAttribute("attributes") Map<?,?> attributes , @RequestParam Long id) {
    	
    	masterFalcoCondition = masterFalcoConditionService.findById(id);
       
    	model.addAttribute("masterFalcoCondition", masterFalcoCondition);
        
    	
    	return "auth/conditionfalco/edit";
    }


    @PostMapping("masterfalcoconditionedit")
    public String editPost(MasterFalcoCondition masterFalcoCondition, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
        
    	masterFalcoConditionService.update(masterFalcoCondition);
    	
    	return "redirect:masterfalcoconditionindex";
    }
    
    
    @GetMapping("masterfalcoconditiondelete")
    public String delete(MasterFalcoCondition masterFalcoCondition, Model model, @ModelAttribute("attributes") Map<?,?> attributes , @RequestParam Long id) {
          	
    	masterFalcoConditionService.deleteById(id);
    	
    	return "redirect:masterfalcoconditionindex";
    }
        
    @ModelAttribute("attributes")
    public Map<?,?> attributes() {
        return new HashMap<String,String>();
    }

}
