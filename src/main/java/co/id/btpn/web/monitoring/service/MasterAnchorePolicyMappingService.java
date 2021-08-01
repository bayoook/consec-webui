package co.id.btpn.web.monitoring.service;

import java.util.List;

import co.id.btpn.web.monitoring.model.MasterAnchorePolicyMapping;

public interface MasterAnchorePolicyMappingService {
    
    void save(MasterAnchorePolicyMapping masterAnchorePolicyMapping);

	List<MasterAnchorePolicyMapping> findAll() ;

	MasterAnchorePolicyMapping findById(long pId);
	
	void update(MasterAnchorePolicyMapping masterAnchorePolicyMapping);
	
	void delete(MasterAnchorePolicyMapping masterAnchorePolicyMapping);

	void deleteById(long id);

}
