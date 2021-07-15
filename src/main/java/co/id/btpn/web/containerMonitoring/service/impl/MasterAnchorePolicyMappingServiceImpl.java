package co.id.btpn.web.containerMonitoring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.btpn.web.containerMonitoring.model.MasterAnchorePolicyMapping;
import co.id.btpn.web.containerMonitoring.repository.MasterAnchorePolicyMappingRepository;
import co.id.btpn.web.containerMonitoring.service.MasterAnchorePolicyMappingService;



@Service("masterAnchorePolicyMappingService")
public class MasterAnchorePolicyMappingServiceImpl implements MasterAnchorePolicyMappingService{

	@Autowired
	private MasterAnchorePolicyMappingRepository masterAnchorePolicyMappingRepository;
	

	@Override
	public void save(MasterAnchorePolicyMapping masterAnchorePolicyMapping) {
		masterAnchorePolicyMappingRepository.save(masterAnchorePolicyMapping);
	}

	@Override
	public List<MasterAnchorePolicyMapping> findAll() {
		return  masterAnchorePolicyMappingRepository.findAll();
	}

	@Override
	public MasterAnchorePolicyMapping findById(long pId) {
		return masterAnchorePolicyMappingRepository.findById(pId).orElse(null);
	}
	
	@Override
	public void update(MasterAnchorePolicyMapping masterAnchorePolicyMapping) {
		masterAnchorePolicyMappingRepository.save(masterAnchorePolicyMapping);
	}
	
	@Override
	public void delete(MasterAnchorePolicyMapping masterAnchorePolicyMapping) {
		masterAnchorePolicyMappingRepository.delete(masterAnchorePolicyMapping);
	}

	@Override
	public void deleteById(long pId) {
		masterAnchorePolicyMappingRepository.deleteById(pId);
	}

}
