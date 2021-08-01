package co.id.btpn.web.monitoring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.btpn.web.monitoring.model.MasterAnchoreAction;
import co.id.btpn.web.monitoring.repository.MasterAnchoreActionRepository;
import co.id.btpn.web.monitoring.service.MasterAnchoreActionService;



@Service("masterAnchoreActionService")
public class MasterAnchoreActionServiceImpl implements MasterAnchoreActionService{

	@Autowired
	private MasterAnchoreActionRepository masterAnchoreActionRepository;
	

	@Override
	public void save(MasterAnchoreAction masterAnchoreAction) {
     	masterAnchoreActionRepository.save(masterAnchoreAction);
	}

	@Override
	public List<MasterAnchoreAction> findAll() {
		return  masterAnchoreActionRepository.findAll();
	}

	@Override
	public MasterAnchoreAction findById(long pId) {
		return masterAnchoreActionRepository.findById(pId).orElse(null);
	}
	
	@Override
	public void update(MasterAnchoreAction masterAnchoreAction) {
		masterAnchoreActionRepository.save(masterAnchoreAction);
	}
	
	@Override
	public void delete(MasterAnchoreAction masterAnchoreAction) {
		masterAnchoreActionRepository.delete(masterAnchoreAction);
	}

	@Override
	public void deleteById(long pId) {
		masterAnchoreActionRepository.deleteById(pId);
	}

}
