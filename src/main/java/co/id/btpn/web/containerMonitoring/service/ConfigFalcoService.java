package co.id.btpn.web.containerMonitoring.service;

import java.util.List;
import co.id.btpn.web.containerMonitoring.model.ConfigFalco;

public interface ConfigFalcoService {

	void save(ConfigFalco configFalco);

	List<ConfigFalco> findAll() ;

	ConfigFalco findById(long pId);
	
	void update(ConfigFalco configFalco);
	
	void delete(ConfigFalco configFalco);

	void deleteById(long id);

}
