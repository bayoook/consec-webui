package co.id.btpn.web.monitoring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.btpn.web.monitoring.model.ConfigFalco;
import co.id.btpn.web.monitoring.repository.ConfigFalcoRepository;
import co.id.btpn.web.monitoring.service.ConfigFalcoService;



@Service("configFalcoService")
public class ConfigFalcoServiceImpl implements ConfigFalcoService{

	@Autowired
	private ConfigFalcoRepository configFalcoRepository;
	

	@Override
	public void save(ConfigFalco configFalco) {
     	configFalcoRepository.save(configFalco);
	}

	@Override
	public List<ConfigFalco> findAll() {
		return  configFalcoRepository.findAll();
	}

	@Override
	public ConfigFalco findById(long pId) {
		return configFalcoRepository.findById(pId).orElse(null);
	}
	
	@Override
	public void update(ConfigFalco configFalco) {
		configFalcoRepository.save(configFalco);
	}
	
	@Override
	public void delete(ConfigFalco configFalco) {
		configFalcoRepository.delete(configFalco);
	}

	@Override
	public void deleteById(long pId) {
		configFalcoRepository.deleteById(pId);
	}

}
