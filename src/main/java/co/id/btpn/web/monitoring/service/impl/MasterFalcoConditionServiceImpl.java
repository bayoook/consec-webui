package co.id.btpn.web.monitoring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.btpn.web.monitoring.model.MasterFalcoCondition;
import co.id.btpn.web.monitoring.repository.MasterFalcoConditionRepository;
import co.id.btpn.web.monitoring.service.MasterFalcoConditionService;



@Service("masterFalcoConditionService")
public class MasterFalcoConditionServiceImpl implements MasterFalcoConditionService{

	@Autowired
	private MasterFalcoConditionRepository masterFalcoConditionRepository;
	

	@Override
	public void save(MasterFalcoCondition masterFalcoCondition) {
		masterFalcoConditionRepository.save(masterFalcoCondition);
	}

	@Override
	public List<MasterFalcoCondition> findAll() {
		return  masterFalcoConditionRepository.findAll();
	}

	@Override
	public MasterFalcoCondition findById(long pId) {
		return masterFalcoConditionRepository.findById(pId).orElse(null);
	}
	
	@Override
	public void update(MasterFalcoCondition masterFalcoCondition) {
		masterFalcoConditionRepository.save(masterFalcoCondition);
	}
	
	@Override
	public void delete(MasterFalcoCondition masterFalcoCondition) {
		masterFalcoConditionRepository.delete(masterFalcoCondition);
	}

	@Override
	public void deleteById(long pId) {
		masterFalcoConditionRepository.deleteById(pId);
	}

}
