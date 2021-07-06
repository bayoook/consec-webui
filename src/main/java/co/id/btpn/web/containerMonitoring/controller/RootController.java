package co.id.btpn.web.containerMonitoring.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;


/**
 *
 * @author Ferry Fadly
 */
@Controller
@SessionAttributes("attributes")
public class RootController {

    @Value("${kibana.dashboard.url}")
    private String kibanaUrl;

	
	@GetMapping("/") public String root() { return "login"; }
    @GetMapping("/login") public String login() { return "login"; }
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/dashboard") public String dashboard( Model model, @ModelAttribute("attributes") Map<?,?> attributes) {
        model.addAttribute("kibanaUrl", kibanaUrl);
        return "auth/dashboard"; 
    }


    @GetMapping("scanondemandindex") public String scanOnDemand() { return "auth/scanondemand/index"; }
   
    @GetMapping("scanregistryindex") public String scanRegistry() { return "auth/scanregistry/index"; }

    
    @ModelAttribute("attributes")
    public Map<?,?> attributes() {
        return new HashMap<String,String>();
    }

    


}
