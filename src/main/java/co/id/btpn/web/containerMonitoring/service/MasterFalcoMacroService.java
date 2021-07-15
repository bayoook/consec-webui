package co.id.btpn.web.containerMonitoring.service;

import java.util.List;

import co.id.btpn.web.containerMonitoring.model.MasterFalcoMacro;

public interface MasterFalcoMacroService {
    
    void save(MasterFalcoMacro masterFalcoMacro);

	List<MasterFalcoMacro> findAll() ;

	MasterFalcoMacro findById(long pId);
	
	void update(MasterFalcoMacro masterFalcoMacro);
	
	void delete(MasterFalcoMacro masterFalcoMacro);

	void deleteById(long id);
    
}
