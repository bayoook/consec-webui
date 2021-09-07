package co.id.btpn.web.monitoring.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

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

import io.fabric8.kubernetes.api.model.ConfigMap;
import io.fabric8.kubernetes.api.model.ConfigMapBuilder;
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

    @GetMapping("servicestatusindex") public String serviceStatus( Model model, @ModelAttribute("attributes") Map<?,?> attributes ) throws JsonIOException, IOException { 

        List<PodExt> pods =  new ArrayList<>();

       for (Pod iterable_element : openshiftClientService.getConnection().pods().list().getItems()) {
           PodExt podExt = new PodExt(iterable_element);
           pods.add(podExt);
       }

       //ConfigMap cm =  openshiftClientService.getConnection().configMaps().inNamespace("consec-dev").withName("falco-duplicate").get();
      
      
      

    //    System.out.println("configmap "+cm.getData().get("falco_rules.local.yaml"));

    //   FileWriter fw = new FileWriter("/Users/ferry/Documents/git_local/btpnapp/falco_rules.local.yaml");
    //   fw.write(cm.getData().get("falco_rules.local.yaml"));
    // Path path = Paths.get("/Users/ferry/Documents/git_local/btpnapp/falco_duplicate.yaml");
    // Files.write(path, cm.getData().get("falco_rules.local.yaml").getBytes());

    // path = Paths.get("/Users/ferry/Documents/git_local/btpnapp/application_rules.yaml");
    // Files.write(path, cm.getData().get("application_rules.yaml").getBytes());

    // path = Paths.get("/Users/ferry/Documents/git_local/btpnapp/falco.yaml");
    // Files.write(path, cm.getData().get("falco.yaml").getBytes());

    // path = Paths.get("/Users/ferry/Documents/git_local/btpnapp/falco_rules.yaml");
    // Files.write(path, cm.getData().get("falco_rules.yaml").getBytes());

    // path = Paths.get("/Users/ferry/Documents/git_local/btpnapp/k8s_audit_rules.yaml");
    // Files.write(path, cm.getData().get("k8s_audit_rules.yaml").getBytes());
    
    // path = Paths.get("/Users/ferry/Documents/git_local/btpnapp/mail-options-duplicate.yaml");
    // Files.write(path, cmcustom.getData().get("mail-options.incl").getBytes());


    // ConfigMap cmcustom =  openshiftClientService.getConnection().configMaps().inNamespace("consec-dev").withName("mail-options-duplicate").get();
     
    // Map <String,String> test = new HashMap<String,String>();
    // test.put("file1.yaml", "isi file1");
    // test.put("file2.yaml", "isi file2");
    // test.put("file3.yaml", "isi file3");
    // test.put("file4.yaml", "isi file4");
    
    
    // ConfigMap newConfigMap = new ConfigMapBuilder().withNewMetadata()
    // .withName("configmap-webui")
    // .withNamespace("consec-dev")
    // .addToLabels("app", "falco")
    // .endMetadata()
    // .addToData(test)
    // .build();


    // Properties properties = new Properties();
    // InputStream stream = new ByteArrayInputStream(cmcustom.getData().get("mail-options.incl").getBytes(StandardCharsets.UTF_8));
    // ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    
    // properties.load(stream);

    // System.out.println("dari properties FalcoNOTIFY = "+properties.getProperty("FalcoNOTIFY"));
    // properties.setProperty("FalcoNOTIFY", "false");
    // properties.store(byteArrayOutputStream, "");

    // String out = new String(byteArrayOutputStream.toByteArray(), "UTF-8");

    // System.out.println("dari properties to string = "+out);

    
    // openshiftClientService.getConnection().configMaps().inNamespace("consec-dev").createOrReplace(newConfigMap);

    

   // openshiftClientService.getConnection().configMaps().inNamespace("consec-dev").withName("falco-duplicate").patch(patchContext, item)
    //    Gson gson = new Gson();
    //    gson.toJson(cm.getData(),new FileWriter("/Users/ferry/Documents/git_local/btpnapp/json-configmap.js"));



       model.addAttribute("list", pods); 
       return "auth/servicestatus/index"; 
    }



    
    @ModelAttribute("attributes")
    public Map<?,?> attributes() {
        return new HashMap<String,String>();
    }

    


}
