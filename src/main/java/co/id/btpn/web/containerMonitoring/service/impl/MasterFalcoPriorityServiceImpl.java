package co.id.btpn.web.containerMonitoring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.btpn.web.containerMonitoring.model.MasterFalcoPriority;
import co.id.btpn.web.containerMonitoring.repository.MasterFalcoPriorityRepository;
import co.id.btpn.web.containerMonitoring.service.MasterFalcoPriorityService;



@Service("masterFalcoPriorityService")
public class MasterFalcoPriorityServiceImpl implements MasterFalcoPriorityService{

	@Autowired
	private MasterFalcoPriorityRepository masterFalcoPriorityRepository;
	

	@Override
	public void save(MasterFalcoPriority masterFalcoPriority) {
		masterFalcoPriorityRepository.save(masterFalcoPriority);
	}

	@Override
	public List<MasterFalcoPriority> findAll() {
		return  masterFalcoPriorityRepository.findAll();
	}

	@Override
	public MasterFalcoPriority findById(long pId) {
		return masterFalcoPriorityRepository.findById(pId).orElse(null);
	}
	
	@Override
	public void update(MasterFalcoPriority masterFalcoPriority) {
		masterFalcoPriorityRepository.save(masterFalcoPriority);
	}
	
	@Override
	public void delete(MasterFalcoPriority masterFalcoPriority) {
		masterFalcoPriorityRepository.delete(masterFalcoPriority);
	}

	@Override
	public void deleteById(long pId) {
		masterFalcoPriorityRepository.deleteById(pId);
	}

}
