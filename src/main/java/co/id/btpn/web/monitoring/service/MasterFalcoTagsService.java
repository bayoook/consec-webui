package co.id.btpn.web.monitoring.service;

import java.util.List;

import co.id.btpn.web.monitoring.model.MasterFalcoTags;

public interface MasterFalcoTagsService {
    
    void save(MasterFalcoTags masterFalcoTags);

	List<MasterFalcoTags> findAll() ;

	MasterFalcoTags findById(long pId);
	
	void update(MasterFalcoTags masterFalcoTags);
	
	void delete(MasterFalcoTags masterFalcoTags);

	void deleteById(long id);
    
}
