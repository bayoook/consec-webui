package co.id.btpn.web.containerMonitoring.service;

import java.util.List;

import co.id.btpn.web.containerMonitoring.model.MasterAnchoreTrigger;

public interface MasterAnchoreTriggerService {
    
    void save(MasterAnchoreTrigger masterAnchoreTrigger);

	List<MasterAnchoreTrigger> findAll() ;

	MasterAnchoreTrigger findById(long pId);
	
	void update(MasterAnchoreTrigger masterAnchoreTrigger);
	
	void delete(MasterAnchoreTrigger masterAnchoreTrigger);

	void deleteById(long id);
    
}
