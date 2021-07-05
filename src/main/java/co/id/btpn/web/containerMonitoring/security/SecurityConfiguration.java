package co.id.btpn.web.containerMonitoring.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import co.id.btpn.web.containerMonitoring.model.Userapp;
import co.id.btpn.web.containerMonitoring.repository.UserappRepository;
import co.id.btpn.web.containerMonitoring.service.UserappService;

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

	@Value("${ldap.urls}")
    private String ldapUrls;

    @Value("${ldap.base.dn}")
    private String ldapBaseDn;

	@Value("${ldap.base.groupsearchbase}")
    private String groupSearchBase;
 
    @Value("${ldap.user.dn.pattern}")
    private String ldapUserDnPattern;
	
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


		System.out.println("Connecting to LDAP server " + ldapUrls + ldapBaseDn);
		System.out.println("LDAP User DN Pattern " + ldapUserDnPattern);
		System.out.println("LDAP search base " + groupSearchBase);

		auth
		.ldapAuthentication()
		.userDetailsContextMapper(userDetailsContextMapper())
		//  .userDnPatterns("uid={0},ou=people")
		  .userDnPatterns(ldapUserDnPattern)
		  .groupSearchBase(groupSearchBase)
		  .contextSource()
		//	.url("ldap://localhost:10389/dc=example,dc=com")
			.url(ldapUrls + ldapBaseDn)
			.and()
		  .passwordCompare()
			//.passwordEncoder(new BCryptPasswordEncoder())
			//.passwordEncoder( new LdapShaPasswordEncoder())
			.passwordAttribute("userPassword");

	
	}

	

	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.
			authorizeRequests()
			    .antMatchers("/auth/**").hasAnyAuthority("ADMIN", "USER")
				.antMatchers("/").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/layout").hasAnyAuthority("ADMIN", "USER")
				.and().csrf().disable().formLogin()
				.loginPage("/login").failureUrl("/login?error=true")
				.defaultSuccessUrl("/dashboard")
				.usernameParameter("username")
				.passwordParameter("password")
				.and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/").and().exceptionHandling()
				.accessDeniedPage("/access-denied");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	       .ignoring()
	       .antMatchers("/resources/**","/loginmf/**","/manifest/**","/assets/**","/error/**", "/static/**", "/css/**", "/js/**", "/plugins/**" ,"/media/**","/custom/**","/fonts/**");
	}


	// @Bean
    // public UserDetailsContextMapper userDetailsContextMapper() {
    //     return new LdapUserDetailsMapper() {
    //         @Override
    //         public UserDetails mapUserFromContext(DirContextOperation
	// 		s ctx, String username, Collection<? extends GrantedAuthority> authorities) {

	// 			List<GrantedAuthority>

	// 			UserDetails details = userServiceImpl.loadUserByUsername(username);
	// 			// UserDetails details= userappService.findByName(username);
    //             return  details;
    //         }
    //     };
    // }

	@Bean
    public UserDetailsContextMapper userDetailsContextMapper() {
        return new LdapUserDetailsMapper() {
            @Override
            public UserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection<? extends GrantedAuthority> authorities) {
				List<GrantedAuthority> loadedAuthorities =  loadUserByUsername(username);
                UserDetails details = super.mapUserFromContext(ctx, username, loadedAuthorities);
                return details;
            }
        };
    }

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public List<GrantedAuthority>  loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        Userapp user = (Userapp) userappRepository.findByName(username).get(0);
        List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getRoleId().getRole()));
        return  authorities;
    }

}

