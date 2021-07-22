package co.id.btpn.web.containerMonitoring.service;

import java.util.List;

import co.id.btpn.web.containerMonitoring.model.MasterFalcoTags;

public interface MasterFalcoTagsService {
    
    void save(MasterFalcoTags masterFalcoTags);

	List<MasterFalcoTags> findAll() ;

	MasterFalcoTags findById(long pId);
	
	void update(MasterFalcoTags masterFalcoTags);
	
	void delete(MasterFalcoTags masterFalcoTags);

	void deleteById(long id);
    
}
