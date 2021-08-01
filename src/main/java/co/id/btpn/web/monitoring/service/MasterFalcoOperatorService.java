package co.id.btpn.web.monitoring.service;

import java.util.List;

import co.id.btpn.web.monitoring.model.MasterFalcoOperator;

public interface MasterFalcoOperatorService {
   
    void save(MasterFalcoOperator masterFalcoOperator);

	List<MasterFalcoOperator> findAll() ;

	MasterFalcoOperator findById(long pId);
	
	void update(MasterFalcoOperator masterFalcoOperator);
	
	void delete(MasterFalcoOperator masterFalcoOperator);

	void deleteById(long id);
    
}
