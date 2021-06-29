package co.id.btpn.web.containerMonitoring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.btpn.web.containerMonitoring.model.RuleAnchore;
import co.id.btpn.web.containerMonitoring.repository.RuleAnchoreRepository;
import co.id.btpn.web.containerMonitoring.service.RuleAnchoreService;



@Service("ruleAnchoreService")
public class RuleAnchoreServiceImpl implements RuleAnchoreService{

	@Autowired
	private RuleAnchoreRepository ruleAnchoreRepository;
	

	@Override
	public void save(RuleAnchore ruleAnchore) {
		ruleAnchoreRepository.save(ruleAnchore);
	}

	@Override
	public List<RuleAnchore> findAll() {
		return  ruleAnchoreRepository.findAll();
	}

	@Override
	public RuleAnchore findById(long pId) {
		return ruleAnchoreRepository.findById(pId).orElse(null);
	}
	
	@Override
	public void update(RuleAnchore ruleAnchore) {
		ruleAnchoreRepository.save(ruleAnchore);
	}
	
	@Override
	public void delete(RuleAnchore ruleAnchore) {
		ruleAnchoreRepository.delete(ruleAnchore);
	}

	@Override
	public void deleteById(long pId) {
		ruleAnchoreRepository.deleteById(pId);
	}

}
