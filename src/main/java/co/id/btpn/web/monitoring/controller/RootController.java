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
import javax.naming.InvalidNameException;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import io.fabric8.kubernetes.api.model.ConfigMap;
import io.fabric8.kubernetes.api.model.ConfigMapBuilder;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.openshift.client.OpenShiftClient;
import co.id.btpn.web.monitoring.model.PodExt;
import co.id.btpn.web.monitoring.model.image.Image;
import co.id.btpn.web.monitoring.security.CustomLdapUserDetails;
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

    @GetMapping("/")
    public String root() {
        return "login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/access-denied")
    public String denied() {
        return "access-denied";
    }

    

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/dashboard")
    public String dashboard(HttpServletRequest request, Model model, @ModelAttribute("attributes") Map<?, ?> attributes)
            throws JsonProcessingException, InvalidNameException {

        HttpSession session = request.getSession();

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String dn = "";
        if (principal instanceof CustomLdapUserDetails) {
            dn = ((CustomLdapUserDetails) principal).getDn();
        }

        LdapName dnObj = new LdapName(dn);

        Boolean found = false;
        for (Rdn rdn : dnObj.getRdns()) {
            if (rdn.getType().equalsIgnoreCase("CN")) {
                session.setAttribute("userName", rdn.getValue());
                found = true;
                break;
            }
        }
        if (Boolean.FALSE.equals(found)) {
            if (principal instanceof org.springframework.security.core.userdetails.User) {
                session.setAttribute("userName",
                        ((org.springframework.security.core.userdetails.User) principal).getUsername());
            } else if (principal instanceof CustomLdapUserDetails) {
                session.setAttribute("userName", ((CustomLdapUserDetails) principal).getUsername());
            }
        }

        if (principal instanceof CustomLdapUserDetails) {
            session.setAttribute("userMail", ((CustomLdapUserDetails) principal).getMail());
            session.setAttribute("userThumbnailPhoto", ((CustomLdapUserDetails) principal).getThumbnailPhoto());
        }
        model.addAttribute("kibanaUrl", kibanaUrl);
        return "auth/dashboard";
    }

    @GetMapping("servicestatusindex")
    public String serviceStatus(Model model, @ModelAttribute("attributes") Map<?, ?> attributes)
            throws JsonIOException, IOException {

        List<PodExt> pods = new ArrayList<>();

        for (Pod iterable_element : openshiftClientService.getConnection().pods().list().getItems()) {
            PodExt podExt = new PodExt(iterable_element);
            pods.add(podExt);
        }

        model.addAttribute("list", pods);
        return "auth/servicestatus/index";
    }

    @ModelAttribute("attributes")
    public Map<String, String> attributes() {
        return new HashMap<String, String>();
    }

}
