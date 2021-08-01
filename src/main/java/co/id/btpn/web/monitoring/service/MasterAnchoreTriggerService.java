package co.id.btpn.web.monitoring.service;

import java.util.List;

import co.id.btpn.web.monitoring.model.MasterAnchoreTrigger;

public interface MasterAnchoreTriggerService {
    
    void save(MasterAnchoreTrigger masterAnchoreTrigger);

	List<MasterAnchoreTrigger> findAll() ;

	MasterAnchoreTrigger findById(long pId);
	
	void update(MasterAnchoreTrigger masterAnchoreTrigger);
	
	void delete(MasterAnchoreTrigger masterAnchoreTrigger);

	void deleteById(long id);
    
}
