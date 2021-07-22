package co.id.btpn.web.containerMonitoring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.btpn.web.containerMonitoring.model.MasterFalcoTags;
import co.id.btpn.web.containerMonitoring.repository.MasterFalcoTagsRepository;
import co.id.btpn.web.containerMonitoring.service.MasterFalcoTagsService;



@Service("masterFalcoTagsServiceImpl")
public class MasterFalcoTagsServiceImpl implements MasterFalcoTagsService{

	@Autowired
	private MasterFalcoTagsRepository masterFalcoTagsRepository;
	

	@Override
	public void save(MasterFalcoTags masterFalcoTags) {
		masterFalcoTagsRepository.save(masterFalcoTags);
	}

	@Override
	public List<MasterFalcoTags> findAll() {
		return  masterFalcoTagsRepository.findAll();
	}

	@Override
	public MasterFalcoTags findById(long pId) {
		return masterFalcoTagsRepository.findById(pId).orElse(null);
	}
	
	@Override
	public void update(MasterFalcoTags masterFalcoTags) {
		masterFalcoTagsRepository.save(masterFalcoTags);
	}
	
	@Override
	public void delete(MasterFalcoTags MasterFalcoTags) {
		masterFalcoTagsRepository.delete(MasterFalcoTags);
	}

	@Override
	public void deleteById(long pId) {
		masterFalcoTagsRepository.deleteById(pId);
	}

}
