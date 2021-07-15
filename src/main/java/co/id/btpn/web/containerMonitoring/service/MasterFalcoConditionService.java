package co.id.btpn.web.containerMonitoring.service;

import java.util.List;

import co.id.btpn.web.containerMonitoring.model.MasterFalcoCondition;

public interface MasterFalcoConditionService {
    
    void save(MasterFalcoCondition masterFalcoCondition);

	List<MasterFalcoCondition> findAll() ;

	MasterFalcoCondition findById(long pId);
	
	void update(MasterFalcoCondition masterFalcoCondition);
	
	void delete(MasterFalcoCondition masterFalcoCondition);

	void deleteById(long id);
    
}
