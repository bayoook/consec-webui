package co.id.btpn.web.monitoring.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.naming.directory.Attributes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.support.LdapEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import co.id.btpn.web.monitoring.model.Userapp;
import co.id.btpn.web.monitoring.repository.UserappRepository;
import co.id.btpn.web.monitoring.service.UserappService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import org.springframework.core.env.Environment;

/**
 *
 * @author Ferry Fadly
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	UserappService userappService;

    @Autowired
    UserappRepository userappRepository;
	
	@Autowired
	Environment env;

	// @Autowired
	// CustomLdapAuthoritiesPopulator customLdapAuthoritiesPopulator;

	private static final Logger LOG = LoggerFactory.getLogger(SecurityConfiguration.class);
   
	@Value("${spring.ldap.urls}")
    private String ldapUrls;

    @Value("${spring.ldap.base}")
    private String ldapBase;

    @Value("${spring.ldap.username}")
    private String springLdapUsername;

    @Value("${spring.ldap.password}")
    private String springLdapPassword;

	@Value("${ldap.base.dn.search}")
    private String ldapBaseDnSearch;

	@Value("${ldap.base.dn.search.filter}")
    private String ldapBaseDnSearchFilter;
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		String password = passwordEncoder().encode("password");
		//TODO: Delete before deploy to production
		auth
            .inMemoryAuthentication()
                .withUser("admin").password(password).roles("ADMIN");

		auth
            .inMemoryAuthentication()
                .withUser("user").password(password).roles("USER");


		LOG.info("Connecting to LDAP server {}", ldapUrls );
		LOG.info("LDAP search base {} " , ldapBaseDnSearch);


		auth
		.ldapAuthentication()
		.userDetailsContextMapper(userDetailsContextMapper())
		.ldapAuthoritiesPopulator(getCustomLdapAuthoritiesPopulator())
		.userSearchBase(ldapBaseDnSearch)
        .userSearchFilter(ldapBaseDnSearchFilter)
		  .contextSource()
			.url(ldapUrls)
			.managerDn(springLdapUsername)
            .managerPassword(springLdapPassword);

	
	}

	

	@Autowired
    private CustomLoginListener customLoginListener;

	@Autowired
    private CustomLoginFailedListener customLoginFailedListener;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.headers()
				.frameOptions().disable()
				.and()
			.authorizeRequests()
			    .antMatchers("/auth/**").hasAnyAuthority("ADMIN", "USER")
				.antMatchers("/").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/layout").hasAnyAuthority("ADMIN", "USER")
				.and().csrf().disable().formLogin()
				.loginPage("/login").failureUrl("/login?error=true")
				.successHandler(customLoginListener)
				.failureHandler(customLoginFailedListener)
				.defaultSuccessUrl("/dashboard")
				.usernameParameter("username")
				.passwordParameter("password")
				.and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/").and().exceptionHandling()
				.accessDeniedPage("/access-denied")
				.and()
				.sessionManagement()
                .maximumSessions(1)
                .sessionRegistry(sessionRegistry())
                .maxSessionsPreventsLogin(true)
                .expiredUrl("/logout");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	       .ignoring()
	       .antMatchers("/resources/**","/loginmf/**","/manifest/**","/assets/**","/error/**", "/static/**", "/css/**", "/js/**", "/plugins/**" ,"/media/**","/custom/**","/fonts/**");
	}

	@Bean                           // bean for http session listener
    public HttpSessionListener httpSessionListener() {
        return new HttpSessionListener() {
            @Override
            public void sessionCreated(HttpSessionEvent se) {               // This method will be called when session created
                LOG.info(">>>>> Session Created with session id {}" , se.getSession().getId());
            }

            @Override
            public void sessionDestroyed(HttpSessionEvent se) {         // This method will be automatically called when session destroyed
                LOG.info(">>>>> Session Destroyed, Session id {}" , se.getSession().getId());

                HttpSession session= se.getSession();
                SecurityContextHolder.clearContext();
                     session= se.getSession();
                    if(session != null) {
                        session.invalidate();
                        try {
                        	sessionRegistry().getSessionInformation(session.getId()).expireNow();
                        }catch(Exception e) {

                        }
                    }


            }
        };
    }

	@Bean
    public UserDetailsContextMapper userDetailsContextMapper() {
        return new LdapUserDetailsMapper() {
            @Override
            public UserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection<? extends GrantedAuthority> authorities) {
				List<GrantedAuthority> loadedAuthorities =  loadUserByUsername(username);
				UserDetails details = super.mapUserFromContext(ctx, username, loadedAuthorities);
				CustomLdapUserDetails details2 = new CustomLdapUserDetails((LdapUserDetails) details, env);
				details2.setMail(ctx.getStringAttribute("mail"));
			//	details2.setThumbnailPhoto(LdapEncoder.printBase64Binary((byte[])ctx.getObjectAttribute("thumbnailPhoto")).replaceAll("\\s+","").replaceAll("[\\n\\t ]", ""));


                return details2;
            }
			
        };
    }

	@Bean
	public SessionRegistry sessionRegistry() {
	    return new SessionRegistryImpl();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public List<GrantedAuthority>  loadUserByUsername(String username) throws UsernameNotFoundException {
		List<Userapp>  userapp = userappRepository.findByName(username);
      
        List<GrantedAuthority> authorities = new ArrayList<>();
		if(userapp.isEmpty()){
			//authorities.add(new SimpleGrantedAuthority("USER"));
		}else{
			if(userapp.get(0).getActive() == 1){
				Userapp user = userapp.get(0);
				authorities.add(new SimpleGrantedAuthority(user.getRoleId().getRole()));
			}
		}
		
        return  authorities;
    }


	@Bean
    public CustomLdapAuthoritiesPopulator getCustomLdapAuthoritiesPopulator() {
        return new CustomLdapAuthoritiesPopulator();
    }

	private class CustomLdapAuthoritiesPopulator implements LdapAuthoritiesPopulator {
 
		@Override
		public Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations userData, String username) {
			
			return loadUserByUsername(username);
		}
	}

}

