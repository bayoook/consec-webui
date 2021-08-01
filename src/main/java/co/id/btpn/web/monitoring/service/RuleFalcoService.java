package co.id.btpn.web.monitoring.service;

import java.util.List;

import co.id.btpn.web.monitoring.model.RuleFalco;

public interface RuleFalcoService {

	void save(RuleFalco policyFalco);

	List<RuleFalco> findAll() ;

	RuleFalco findById(long pId);
	
	void update(RuleFalco policyFalco);
	
	void delete(RuleFalco policyFalco);

	void deleteById(long id);

}
