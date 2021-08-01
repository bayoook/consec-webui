package co.id.btpn.web.monitoring.service;

import java.util.List;

import co.id.btpn.web.monitoring.model.RuleParamsAnchore;

public interface RuleParamsAnchoreService {

	void save(RuleParamsAnchore ruleParamsAnchore);

	List<RuleParamsAnchore> findAll() ;

	RuleParamsAnchore findById(long pId);
	
	void update(RuleParamsAnchore ruleParamsAnchore);
	
	void delete(RuleParamsAnchore ruleParamsAnchore);

	void deleteById(long id);

}
