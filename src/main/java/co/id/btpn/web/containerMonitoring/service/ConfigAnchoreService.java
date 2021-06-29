package co.id.btpn.web.containerMonitoring.service;

import java.util.List;
import co.id.btpn.web.containerMonitoring.model.ConfigAnchore;

public interface ConfigAnchoreService {

	void save(ConfigAnchore configAnchore);

	List<ConfigAnchore> findAll() ;

	ConfigAnchore findById(long pId);
	
	void update(ConfigAnchore configAnchore);
	
	void delete(ConfigAnchore configAnchore);

	void deleteById(long id);

}
