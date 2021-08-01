package co.id.btpn.web.monitoring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.btpn.web.monitoring.model.MasterFalcoList;
import co.id.btpn.web.monitoring.repository.MasterFalcoListRepository;
import co.id.btpn.web.monitoring.service.MasterFalcoListService;



@Service("masterFalcoListService")
public class MasterFalcoListServiceImpl implements MasterFalcoListService{

	@Autowired
	private MasterFalcoListRepository masterFalcoListRepository;
	

	@Override
	public void save(MasterFalcoList masterFalcoList) {
		masterFalcoListRepository.save(masterFalcoList);
	}

	@Override
	public List<MasterFalcoList> findAll() {
		return  masterFalcoListRepository.findAll();
	}

	@Override
	public MasterFalcoList findById(long pId) {
		return masterFalcoListRepository.findById(pId).orElse(null);
	}
	
	@Override
	public void update(MasterFalcoList masterFalcoList) {
		masterFalcoListRepository.save(masterFalcoList);
	}
	
	@Override
	public void delete(MasterFalcoList masterFalcoList) {
		masterFalcoListRepository.delete(masterFalcoList);
	}

	@Override
	public void deleteById(long pId) {
		masterFalcoListRepository.deleteById(pId);
	}

}
