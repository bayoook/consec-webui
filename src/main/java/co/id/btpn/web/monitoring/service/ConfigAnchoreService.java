package co.id.btpn.web.monitoring.service;

import java.util.List;

import co.id.btpn.web.monitoring.model.ConfigAnchore;

public interface ConfigAnchoreService {

	void save(ConfigAnchore configAnchore);

	List<ConfigAnchore> findAll() ;

	ConfigAnchore findById(long pId);
	
	void update(ConfigAnchore configAnchore);
	
	void delete(ConfigAnchore configAnchore);

	void deleteById(long id);

}
