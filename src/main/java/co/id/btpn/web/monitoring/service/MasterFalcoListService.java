package co.id.btpn.web.monitoring.service;

import java.util.List;

import co.id.btpn.web.monitoring.model.MasterFalcoList;

public interface MasterFalcoListService {
 
    void save(MasterFalcoList masterFalcoList);

	List<MasterFalcoList> findAll() ;

	MasterFalcoList findById(long pId);
	
	void update(MasterFalcoList masterFalcoList);
	
	void delete(MasterFalcoList masterFalcoList);

	void deleteById(long id);
    
}
