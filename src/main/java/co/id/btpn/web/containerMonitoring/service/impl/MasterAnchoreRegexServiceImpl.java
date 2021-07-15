package co.id.btpn.web.containerMonitoring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.btpn.web.containerMonitoring.model.MasterAnchoreRegex;
import co.id.btpn.web.containerMonitoring.repository.MasterAnchoreRegexRepository;
import co.id.btpn.web.containerMonitoring.service.MasterAnchoreRegexService;



@Service("masterAnchoreRegexService")
public class MasterAnchoreRegexServiceImpl implements MasterAnchoreRegexService{

	@Autowired
	private MasterAnchoreRegexRepository masterAnchoreRegexRepository;
	

	@Override
	public void save(MasterAnchoreRegex masterAnchoreRegex) {
		masterAnchoreRegexRepository.save(masterAnchoreRegex);
	}

	@Override
	public List<MasterAnchoreRegex> findAll() {
		return  masterAnchoreRegexRepository.findAll();
	}

	@Override
	public MasterAnchoreRegex findById(long pId) {
		return masterAnchoreRegexRepository.findById(pId).orElse(null);
	}
	
	@Override
	public void update(MasterAnchoreRegex masterAnchoreRegex) {
		masterAnchoreRegexRepository.save(masterAnchoreRegex);
	}
	
	@Override
	public void delete(MasterAnchoreRegex masterAnchoreRegex) {
		masterAnchoreRegexRepository.delete(masterAnchoreRegex);
	}

	@Override
	public void deleteById(long pId) {
		masterAnchoreRegexRepository.deleteById(pId);
	}

}
