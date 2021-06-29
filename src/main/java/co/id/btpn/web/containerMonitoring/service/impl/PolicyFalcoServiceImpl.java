package co.id.btpn.web.containerMonitoring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.btpn.web.containerMonitoring.model.PolicyFalco;
import co.id.btpn.web.containerMonitoring.repository.PolicyFalcoRepository;
import co.id.btpn.web.containerMonitoring.service.PolicyFalcoService;



@Service("policyFalcoService")
public class PolicyFalcoServiceImpl implements PolicyFalcoService{

	@Autowired
	private PolicyFalcoRepository policyFalcoRepository;
	

	@Override
	public void save(PolicyFalco policyFalco) {
		policyFalcoRepository.save(policyFalco);
	}

	@Override
	public List<PolicyFalco> findAll() {
		return  policyFalcoRepository.findAll();
	}

	@Override
	public PolicyFalco findById(long pId) {
		return policyFalcoRepository.findById(pId).orElse(null);
	}
	
	@Override
	public void update(PolicyFalco policyFalco) {
		policyFalcoRepository.save(policyFalco);
	}
	
	@Override
	public void delete(PolicyFalco policyFalco) {
		policyFalcoRepository.delete(policyFalco);
	}

	@Override
	public void deleteById(long pId) {
		policyFalcoRepository.deleteById(pId);
	}

}
