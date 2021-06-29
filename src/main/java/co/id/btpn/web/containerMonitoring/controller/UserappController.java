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

import co.id.btpn.web.containerMonitoring.model.Role;
import co.id.btpn.web.containerMonitoring.model.Userapp;
import co.id.btpn.web.containerMonitoring.service.RoleService;
import co.id.btpn.web.containerMonitoring.service.UserappService;



/**
 *
 * @author Ferry Fadly
 */
@Controller
@SessionAttributes("attributes")
public class UserappController {

	@Autowired
	UserappService userappService;

    @Autowired
	RoleService roleService;
	
	

    @GetMapping("userappindex")
    public String index(Userapp  userapp, Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
      
    	List <Userapp> list= userappService.findAll();
    	model.addAttribute("list", list);
        
    	return "auth/userapp/index";
    }
    
    @GetMapping("userappadd")
    public String add(Userapp  userapp, Model model, @ModelAttribute("attributes") Map<?,?>  attributes) {
        
    	List <Role> list= roleService.findAll();
    	model.addAttribute("roleList", list);
        
    	
    	return "auth/userapp/add";
    }


    @PostMapping("userappadd")
    public String addPost(Userapp  userapp, Model model, @ModelAttribute("attributes") Map<?,?>  attributes) {
        
    	userappService.save(userapp);;
    	
    	return "redirect:userappindex";
    }
    
    
    @GetMapping("userappedit")
    public String edit(Userapp  userapp, Model model, @ModelAttribute("attributes") Map<?,?>  attributes , @RequestParam Long id) {
        

    	List <Role> list= roleService.findAll();
    	model.addAttribute("roleList", list);
    	
    	userapp = userappService.findById(id);
    	
    	model.addAttribute("userapp", userapp);
        
    	
    	return "auth/userapp/edit";
    }


    @PostMapping("userappedit")
    public String editPost(Userapp  userapp, Model model, @ModelAttribute("attributes") Map<?,?>  attributes) {
        
    	userappService.update(userapp);;
    	
    	return "redirect:userappindex";
    }
    
    
    @GetMapping("userappdelete")
    public String delete(Userapp  userapp, Model model, @ModelAttribute("attributes") Map<?,?>  attributes , @RequestParam Long id) {
          	
    	userappService.deactiveById(id);
    	
    	return "redirect:userappindex";
    }
        
    @ModelAttribute("attributes")
    public Map<?,?>  attributes() {
        return new HashMap<String,String>();
    }

}
