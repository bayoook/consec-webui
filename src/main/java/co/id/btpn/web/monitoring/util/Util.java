package co.id.btpn.web.monitoring.util;

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
}
