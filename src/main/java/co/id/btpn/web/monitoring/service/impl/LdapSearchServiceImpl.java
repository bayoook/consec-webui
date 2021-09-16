package co.id.btpn.web.monitoring.service.impl;


import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.VersionInfo;
import io.fabric8.openshift.client.OpenShiftClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import co.id.btpn.web.monitoring.service.LdapSearchService;
import co.id.btpn.web.monitoring.model.Person;


import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.SearchScope;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Service;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import java.util.List;
import static org.springframework.ldap.query.LdapQueryBuilder.query;

import org.springframework.beans.factory.annotation.Autowired;

@Service("ldapSearchService")
public class LdapSearchServiceImpl implements LdapSearchService{

	    private static final Logger logger = LoggerFactory.getLogger(LdapSearchServiceImpl.class);

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

        @Value("${ldap.base.dn.search.filter2}")
        private String ldapBaseDnSearchFilter2;


        private static final Integer THREE_SECONDS = 3000;

        @Autowired
        private LdapTemplate ldapTemplate;
    


        @Override
        public List<String> getPersonNamesByAccountName(String search) {
        
                LdapQuery query = query()
                        .searchScope(SearchScope.SUBTREE)
                        .timeLimit(THREE_SECONDS)
                        .countLimit(10)
                        .base(LdapUtils.emptyLdapName())
                        .where(""+ldapBaseDnSearchFilter2).like(search);
        
                return ldapTemplate.search(query, new MultipleAttributesMapper());
           
        }

  
	private class IsFoundMapper implements AttributesMapper<Boolean> {

		@Override
		public Boolean mapFromAttributes(Attributes attrs) throws NamingException {
			NamingEnumeration<? extends Attribute> all = attrs.getAll();
            int count =  0;
			while (all.hasMore()) {
				Attribute id = all.next();
                count++;
			}
			
			return count>0 ? true:false;
		}
	}
   


	private class MultipleAttributesMapper implements AttributesMapper<String> {

		@Override
		public String mapFromAttributes(Attributes attrs) throws NamingException {
			NamingEnumeration<? extends Attribute> all = attrs.getAll();
			StringBuffer result = new StringBuffer();
			result.append("\n Result { \n");
			while (all.hasMore()) {
				Attribute id = all.next();
				result.append(" \t |_  #" + id.getID() + "= [ " + id.get() + " ]  \n");
				//logger.info(id.getID() + "\t | " + id.get());
			}
			result.append("\n } ");
			return result.toString();
		}
	}

    
    
}
