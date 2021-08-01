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

import co.id.btpn.web.monitoring.model.MasterFalcoMacro;
import co.id.btpn.web.monitoring.service.MasterFalcoMacroService;



/**
 *
 * @author Ferry Fadly
 */
@Controller
@SessionAttributes("attributes")
public class MasterFalcoMacroController {

	@Autowired
	MasterFalcoMacroService masterFalcoMacroService;
	

    @GetMapping("masterfalcomacroindex")
    public String index(MasterFalcoMacro policyAnchore, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
      
    	List <MasterFalcoMacro> list= masterFalcoMacroService.findAll();
    	model.addAttribute("list", list);
        
    	return "auth/macrofalco/index";
    }
    
    @GetMapping("masterfalcomacroadd")
    public String add(MasterFalcoMacro masterFalcoMacro, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
        
    	
    	return "auth/macrofalco/add";
    }


    @PostMapping("masterfalcomacroadd")
    public String addPost(MasterFalcoMacro masterFalcoMacro, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
        
    	masterFalcoMacroService.save(masterFalcoMacro);;
    	
    	return "redirect:masterfalcomacroindex";
    }
    
    
    @GetMapping("masterfalcomacroedit")
    public String edit(MasterFalcoMacro masterFalcoMacro, Model model, @ModelAttribute("attributes") Map<?,?> attributes , @RequestParam Long id) {
    	
    	masterFalcoMacro = masterFalcoMacroService.findById(id);
       
    	model.addAttribute("masterFalcoMacro", masterFalcoMacro);
        
    	
    	return "auth/macrofalco/edit";
    }


    @PostMapping("masterfalcomacroedit")
    public String editPost(MasterFalcoMacro masterFalcoMacro, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
        
    	masterFalcoMacroService.update(masterFalcoMacro);
    	
    	return "redirect:masterfalcomacroindex";
    }
    
    
    @GetMapping("masterfalcomacrodelete")
    public String delete(MasterFalcoMacro masterFalcoMacro, Model model, @ModelAttribute("attributes") Map<?,?> attributes , @RequestParam Long id) {
          	
    	masterFalcoMacroService.deleteById(id);
    	
    	return "redirect:masterfalcomacroindex";
    }
        
    @ModelAttribute("attributes")
    public Map<?,?> attributes() {
        return new HashMap<String,String>();
    }

}
