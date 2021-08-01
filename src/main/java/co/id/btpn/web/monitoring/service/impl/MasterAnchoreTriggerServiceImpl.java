package co.id.btpn.web.monitoring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.btpn.web.monitoring.model.MasterAnchoreTrigger;
import co.id.btpn.web.monitoring.repository.MasterAnchoreTriggerRepository;
import co.id.btpn.web.monitoring.service.MasterAnchoreTriggerService;



@Service("masterAnchoreTriggerService")
public class MasterAnchoreTriggerServiceImpl implements MasterAnchoreTriggerService{

	@Autowired
	private MasterAnchoreTriggerRepository masterAnchoreTriggerRepository;
	

	@Override
	public void save(MasterAnchoreTrigger masterAnchoreTrigger) {
		masterAnchoreTriggerRepository.save(masterAnchoreTrigger);
	}

	@Override
	public List<MasterAnchoreTrigger> findAll() {
		return  masterAnchoreTriggerRepository.findAll();
	}

	@Override
	public MasterAnchoreTrigger findById(long pId) {
		return masterAnchoreTriggerRepository.findById(pId).orElse(null);
	}
	
	@Override
	public void update(MasterAnchoreTrigger masterAnchoreTrigger) {
		masterAnchoreTriggerRepository.save(masterAnchoreTrigger);
	}
	
	@Override
	public void delete(MasterAnchoreTrigger masterAnchoreTrigger) {
		masterAnchoreTriggerRepository.delete(masterAnchoreTrigger);
	}

	@Override
	public void deleteById(long pId) {
		masterAnchoreTriggerRepository.deleteById(pId);
	}

}
