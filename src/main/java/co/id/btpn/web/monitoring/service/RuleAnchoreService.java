package co.id.btpn.web.monitoring.service;

import java.util.List;

import co.id.btpn.web.monitoring.model.RuleAnchore;

public interface RuleAnchoreService {

	void save(RuleAnchore ruleAnchore);

	List<RuleAnchore> findAll() ;

	RuleAnchore findById(long pId);
	
	void update(RuleAnchore ruleAnchore);
	
	void delete(RuleAnchore ruleAnchore);

	void deleteById(long id);

}
