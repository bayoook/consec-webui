package co.id.btpn.web.monitoring.service;

import java.util.List;

import co.id.btpn.web.monitoring.model.ConfigFalco;

public interface ConfigFalcoService {

	void save(ConfigFalco configFalco);

	List<ConfigFalco> findAll() ;

	ConfigFalco findById(long pId);
	
	void update(ConfigFalco configFalco);
	
	void delete(ConfigFalco configFalco);

	void deleteById(long id);

}
