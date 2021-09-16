package co.id.btpn.web.monitoring.service;

import co.id.btpn.web.monitoring.model.Person;
import java.util.List;

public interface LdapSearchService {
	List<String> getPersonNamesByAccountName(String search);
}
