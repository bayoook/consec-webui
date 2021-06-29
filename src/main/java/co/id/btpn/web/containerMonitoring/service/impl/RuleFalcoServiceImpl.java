package co.id.btpn.web.containerMonitoring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.btpn.web.containerMonitoring.model.RuleFalco;
import co.id.btpn.web.containerMonitoring.repository.RuleFalcoRepository;
import co.id.btpn.web.containerMonitoring.service.RuleFalcoService;



@Service("ruleFalcoService")
public class RuleFalcoServiceImpl implements RuleFalcoService{

	@Autowired
	private RuleFalcoRepository ruleFalcoRepository;
	

	@Override
	public void save(RuleFalco ruleFalco) {
		ruleFalcoRepository.save(ruleFalco);
	}

	@Override
	public List<RuleFalco> findAll() {
		return  ruleFalcoRepository.findAll();
	}

	@Override
	public RuleFalco findById(long pId) {
		return ruleFalcoRepository.findById(pId).orElse(null);
	}
	
	@Override
	public void update(RuleFalco ruleFalco) {
		ruleFalcoRepository.save(ruleFalco);
	}
	
	@Override
	public void delete(RuleFalco ruleFalco) {
		ruleFalcoRepository.delete(ruleFalco);
	}

	@Override
	public void deleteById(long pId) {
		ruleFalcoRepository.deleteById(pId);
	}

}
