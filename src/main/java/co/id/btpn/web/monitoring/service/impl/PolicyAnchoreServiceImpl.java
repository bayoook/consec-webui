package co.id.btpn.web.monitoring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.btpn.web.monitoring.model.PolicyAnchore;
import co.id.btpn.web.monitoring.repository.PolicyAnchoreRepository;
import co.id.btpn.web.monitoring.service.PolicyAnchoreService;



@Service("policyAnchoreService")
public class PolicyAnchoreServiceImpl implements PolicyAnchoreService{

	@Autowired
	private PolicyAnchoreRepository policyAnchoreRepository;
	

	@Override
	public void save(PolicyAnchore policyAnchore) {
		policyAnchoreRepository.save(policyAnchore);
	}

	@Override
	public List<PolicyAnchore> findAll() {
		return  policyAnchoreRepository.findAll();
	}

	@Override
	public PolicyAnchore findById(long pId) {
		return policyAnchoreRepository.findById(pId).orElse(null);
	}
	
	@Override
	public void update(PolicyAnchore policyAnchore) {
		policyAnchoreRepository.save(policyAnchore);
	}
	
	@Override
	public void delete(PolicyAnchore policyAnchore) {
		policyAnchoreRepository.delete(policyAnchore);
	}

	@Override
	public void deleteById(long pId) {
		policyAnchoreRepository.deleteById(pId);
	}

}
