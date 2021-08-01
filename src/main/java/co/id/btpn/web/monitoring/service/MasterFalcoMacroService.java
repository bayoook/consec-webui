package co.id.btpn.web.monitoring.service;

import java.util.List;

import co.id.btpn.web.monitoring.model.MasterFalcoMacro;

public interface MasterFalcoMacroService {
    
    void save(MasterFalcoMacro masterFalcoMacro);

	List<MasterFalcoMacro> findAll() ;

	MasterFalcoMacro findById(long pId);
	
	void update(MasterFalcoMacro masterFalcoMacro);
	
	void delete(MasterFalcoMacro masterFalcoMacro);

	void deleteById(long id);
    
}
