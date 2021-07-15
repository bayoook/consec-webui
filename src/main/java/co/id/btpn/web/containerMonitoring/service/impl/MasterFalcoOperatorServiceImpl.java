package co.id.btpn.web.containerMonitoring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.btpn.web.containerMonitoring.model.MasterFalcoOperator;
import co.id.btpn.web.containerMonitoring.repository.MasterFalcoOperatorRepository;
import co.id.btpn.web.containerMonitoring.service.MasterFalcoOperatorService;



@Service("masterFalcoOperatorService")
public class MasterFalcoOperatorServiceImpl implements MasterFalcoOperatorService{

	@Autowired
	private MasterFalcoOperatorRepository masterFalcoOperatorRepository;
	

	@Override
	public void save(MasterFalcoOperator masterFalcoOperator) {
		masterFalcoOperatorRepository.save(masterFalcoOperator);
	}

	@Override
	public List<MasterFalcoOperator> findAll() {
		return  masterFalcoOperatorRepository.findAll();
	}

	@Override
	public MasterFalcoOperator findById(long pId) {
		return masterFalcoOperatorRepository.findById(pId).orElse(null);
	}
	
	@Override
	public void update(MasterFalcoOperator masterFalcoOperator) {
		masterFalcoOperatorRepository.save(masterFalcoOperator);
	}
	
	@Override
	public void delete(MasterFalcoOperator masterFalcoOperator) {
		masterFalcoOperatorRepository.delete(masterFalcoOperator);
	}

	@Override
	public void deleteById(long pId) {
		masterFalcoOperatorRepository.deleteById(pId);
	}

}
