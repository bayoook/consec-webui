package co.id.btpn.web.monitoring.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import co.id.btpn.web.monitoring.security.CustomLdapUserDetails;

@Service
public class Util {

  
    
    public String getLoggedUserName(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof CustomLdapUserDetails) {
            return ((CustomLdapUserDetails) principal).getUsername();
        }else if (principal instanceof UserDetails) {
            return  ((UserDetails)principal).getUsername();
        }else {
            return principal.toString();
        }
    } 

    public boolean isUserLoggedIn() {
        if (getLoggedUserName().equalsIgnoreCase("anonymousUser")) {
            return false;
        }else{
            return true;
        }
    }
}
