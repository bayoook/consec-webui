package co.id.btpn.web.containerMonitoring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.btpn.web.containerMonitoring.model.ConfigAnchore;
import co.id.btpn.web.containerMonitoring.repository.ConfigAnchoreRepository;
import co.id.btpn.web.containerMonitoring.service.ConfigAnchoreService;



@Service("configAnchoreService")
public class ConfigAnchoreServiceImpl implements ConfigAnchoreService{

	@Autowired
	private ConfigAnchoreRepository configAnchoreRepository;
	

	@Override
	public void save(ConfigAnchore configAnchore) {
     	configAnchoreRepository.save(configAnchore);
	}

	@Override
	public List<ConfigAnchore> findAll() {
		return  configAnchoreRepository.findAll();
	}

	@Override
	public ConfigAnchore findById(long pId) {
		return configAnchoreRepository.findById(pId).orElse(null);
	}
	
	@Override
	public void update(ConfigAnchore configAnchore) {
		configAnchoreRepository.save(configAnchore);
	}
	
	@Override
	public void delete(ConfigAnchore configAnchore) {
		configAnchoreRepository.delete(configAnchore);
	}

	@Override
	public void deleteById(long pId) {
		configAnchoreRepository.deleteById(pId);
	}

}
