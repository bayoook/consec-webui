package co.id.btpn.web.monitoring.service;

import java.util.List;

import co.id.btpn.web.monitoring.model.PolicyFalco;

public interface PolicyFalcoService {

	void save(PolicyFalco policyFalco);

	List<PolicyFalco> findAll() ;

	PolicyFalco findById(long pId);
	
	void update(PolicyFalco policyFalco);
	
	void delete(PolicyFalco policyFalco);

	void deleteById(long id);

}
