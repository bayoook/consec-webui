package co.id.btpn.web.monitoring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.btpn.web.monitoring.model.RuleParamsAnchore;
import co.id.btpn.web.monitoring.repository.RuleParamsAnchoreRepository;
import co.id.btpn.web.monitoring.service.RuleParamsAnchoreService;



@Service("ruleParamsAnchoreService")
public class RuleParamsAnchoreServiceImpl implements RuleParamsAnchoreService{

	@Autowired
	private RuleParamsAnchoreRepository ruleParamsAnchoreRepository;
	

	@Override
	public void save(RuleParamsAnchore ruleParamsAnchore) {
		ruleParamsAnchoreRepository.save(ruleParamsAnchore);
	}

	@Override
	public List<RuleParamsAnchore> findAll() {
		return  ruleParamsAnchoreRepository.findAll();
	}

	@Override
	public RuleParamsAnchore findById(long pId) {
		return ruleParamsAnchoreRepository.findById(pId).orElse(null);
	}
	
	@Override
	public void update(RuleParamsAnchore ruleParamsAnchore) {
		ruleParamsAnchoreRepository.save(ruleParamsAnchore);
	}
	
	@Override
	public void delete(RuleParamsAnchore ruleParamsAnchore) {
		ruleParamsAnchoreRepository.delete(ruleParamsAnchore);
	}

	@Override
	public void deleteById(long pId) {
		ruleParamsAnchoreRepository.deleteById(pId);
	}

}
