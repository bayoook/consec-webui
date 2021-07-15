package co.id.btpn.web.containerMonitoring.service;

import java.util.List;

import co.id.btpn.web.containerMonitoring.model.MasterFalcoPriority;

public interface MasterFalcoPriorityService {
    
    void save(MasterFalcoPriority masterFalcoPriority);

	List<MasterFalcoPriority> findAll() ;

	MasterFalcoPriority findById(long pId);
	
	void update(MasterFalcoPriority masterFalcoPriority);
	
	void delete(MasterFalcoPriority masterFalcoPriority);

	void deleteById(long id);
    
}
