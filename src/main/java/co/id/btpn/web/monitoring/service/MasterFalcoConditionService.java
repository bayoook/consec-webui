package co.id.btpn.web.monitoring.service;

import java.util.List;

import co.id.btpn.web.monitoring.model.MasterFalcoCondition;

public interface MasterFalcoConditionService {
    
    void save(MasterFalcoCondition masterFalcoCondition);

	List<MasterFalcoCondition> findAll() ;

	MasterFalcoCondition findById(long pId);
	
	void update(MasterFalcoCondition masterFalcoCondition);
	
	void delete(MasterFalcoCondition masterFalcoCondition);

	void deleteById(long id);
    
}
