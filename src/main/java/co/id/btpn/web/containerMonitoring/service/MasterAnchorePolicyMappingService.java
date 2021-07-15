package co.id.btpn.web.containerMonitoring.service;

import java.util.List;

import co.id.btpn.web.containerMonitoring.model.MasterAnchorePolicyMapping;

public interface MasterAnchorePolicyMappingService {
    
    void save(MasterAnchorePolicyMapping masterAnchorePolicyMapping);

	List<MasterAnchorePolicyMapping> findAll() ;

	MasterAnchorePolicyMapping findById(long pId);
	
	void update(MasterAnchorePolicyMapping masterAnchorePolicyMapping);
	
	void delete(MasterAnchorePolicyMapping masterAnchorePolicyMapping);

	void deleteById(long id);

}
