package co.id.btpn.web.monitoring.controller;

import java.util.Locale;

import javax.naming.InvalidNameException;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.client.RestTemplate;

import co.id.btpn.web.monitoring.security.CustomLdapUserDetails;


@ControllerAdvice
public class GlobalController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment env;



    @ModelAttribute   // A Model Attribute Adder method
    public void setGlobalModelAttributes(HttpServletRequest request, Model model) throws InvalidNameException {

    	

		 

    }

    public static HttpEntity generateHttpEntity(Object parameter, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+token);
        HttpEntity<String> entity = new HttpEntity(parameter, headers);

        return entity;
    }
}




