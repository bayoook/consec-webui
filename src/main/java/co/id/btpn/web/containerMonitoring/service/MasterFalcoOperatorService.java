package co.id.btpn.web.containerMonitoring.service;

import java.util.List;

import co.id.btpn.web.containerMonitoring.model.MasterFalcoOperator;

public interface MasterFalcoOperatorService {
   
    void save(MasterFalcoOperator masterFalcoOperator);

	List<MasterFalcoOperator> findAll() ;

	MasterFalcoOperator findById(long pId);
	
	void update(MasterFalcoOperator masterFalcoOperator);
	
	void delete(MasterFalcoOperator masterFalcoOperator);

	void deleteById(long id);
    
}
