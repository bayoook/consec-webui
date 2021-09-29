package co.id.btpn.web.monitoring.controller;

import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.client.RestTemplate;

import co.id.btpn.web.monitoring.security.CustomLdapUserDetails;
import co.id.btpn.web.monitoring.util.Util;


@ControllerAdvice
public class GlobalController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment env;


    @Autowired
    private Util util;



    @ModelAttribute   // A Model Attribute Adder method
    public void setGlobalModelAttributes(HttpServletRequest req, HttpServletResponse resp, Model model) throws java.io.IOException, javax.naming.InvalidNameException {
      HttpSession session = req.getSession();

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
    }
}




