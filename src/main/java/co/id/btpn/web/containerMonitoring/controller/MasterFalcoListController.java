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

import co.id.btpn.web.containerMonitoring.model.MasterFalcoList;
import co.id.btpn.web.containerMonitoring.service.MasterFalcoListService;



/**
 *
 * @author Ferry Fadly
 */
@Controller
@SessionAttributes("attributes")
public class MasterFalcoListController {

	@Autowired
	MasterFalcoListService masterFalcoListService;
	

    @GetMapping("masterfalcolistindex")
    public String index(MasterFalcoList masterFalcoList, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
      
    	List <MasterFalcoList> list= masterFalcoListService.findAll();
    	model.addAttribute("list", list);
        
    	return "auth/listfalco/index";
    }
    
    @GetMapping("masterfalcolistadd")
    public String add(MasterFalcoList masterFalcoList, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
        
    	
    	return "auth/listfalco/add";
    }


    @PostMapping("masterfalcolistadd")
    public String addPost(MasterFalcoList masterFalcoList, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
        
    	masterFalcoListService.save(masterFalcoList);
    	
    	return "redirect:masterfalcolistindex";
    }
    
    
    @GetMapping("masterfalcolistedit")
    public String edit(MasterFalcoList masterFalcoList, Model model, @ModelAttribute("attributes") Map<?,?> attributes , @RequestParam Long id) {
    	
    	masterFalcoList = masterFalcoListService.findById(id);
       
    	model.addAttribute("masterFalcoList", masterFalcoList);
        
    	
    	return "auth/listfalco/edit";
    }


    @PostMapping("masterfalcolistedit")
    public String editPost(MasterFalcoList masterFalcoList, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
        
    	masterFalcoListService.update(masterFalcoList);
    	
    	return "redirect:masterfalcolistindex";
    }
    
    
    @GetMapping("masterfalcolistdelete")
    public String delete(MasterFalcoList masterFalcoList, Model model, @ModelAttribute("attributes") Map<?,?> attributes , @RequestParam Long id) {
          	
    	masterFalcoListService.deleteById(id);
    	
    	return "redirect:masterfalcolistindex";
    }
        
    @ModelAttribute("attributes")
    public Map<?,?> attributes() {
        return new HashMap<String,String>();
    }

}
