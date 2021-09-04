package co.id.btpn.web.monitoring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.openshift.client.OpenShiftClient;
import co.id.btpn.web.monitoring.model.PodExt;
import co.id.btpn.web.monitoring.model.image.Image;
import co.id.btpn.web.monitoring.service.OpenshiftClientService;


/**
 *
 * @author Ferry Fadly
 */
@Controller
@SessionAttributes("attributes")
public class RootController {

    @Value("${kibana.dashboard.url}")
    private String kibanaUrl;

    @Autowired
    OpenshiftClientService openshiftClientService;

	
	@GetMapping("/") public String root() { return "login"; }
    @GetMapping("/login") public String login() { return "login"; }
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/dashboard") public String dashboard( Model model, @ModelAttribute("attributes") Map<?,?> attributes) throws JsonProcessingException {

        model.addAttribute("kibanaUrl", kibanaUrl);
        return "auth/dashboard"; 
    }

    @GetMapping("servicestatusindex") public String serviceStatus( Model model, @ModelAttribute("attributes") Map<?,?> attributes ) { 

        List<PodExt> pods =  new ArrayList<>();

       for (Pod iterable_element : openshiftClientService.getConnection().pods().list().getItems()) {
           PodExt podExt = new PodExt(iterable_element);
           pods.add(podExt);
       }

       model.addAttribute("list", pods); 
       return "auth/servicestatus/index"; 
    }



    
    @ModelAttribute("attributes")
    public Map<?,?> attributes() {
        return new HashMap<String,String>();
    }

    


}
