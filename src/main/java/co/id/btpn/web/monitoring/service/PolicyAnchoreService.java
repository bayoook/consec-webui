package co.id.btpn.web.monitoring.service;

import java.util.List;

import co.id.btpn.web.monitoring.model.PolicyAnchore;

public interface PolicyAnchoreService {

	void save(PolicyAnchore policyAnchore);

	List<PolicyAnchore> findAll() ;

	PolicyAnchore findById(long pId);
	
	void update(PolicyAnchore policyAnchore);
	
	void delete(PolicyAnchore policyAnchore);

	void deleteById(long id);

}
