package co.id.btpn.web.monitoring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.btpn.web.monitoring.model.MasterAnchoreWhitelist;
import co.id.btpn.web.monitoring.repository.MasterAnchoreWhitelistRepository;
import co.id.btpn.web.monitoring.service.MasterAnchoreWhitelistService;



@Service("masterAnchoreWhitelistService")
public class MasterAnchoreWhitelistServiceImpl implements MasterAnchoreWhitelistService{

	@Autowired
	private MasterAnchoreWhitelistRepository masterAnchoreWhitelistRepository;
	

	@Override
	public void save(MasterAnchoreWhitelist masterAnchoreWhitelist) {
		masterAnchoreWhitelistRepository.save(masterAnchoreWhitelist);
	}

	@Override
	public List<MasterAnchoreWhitelist> findAll() {
		return  masterAnchoreWhitelistRepository.findAll();
	}

	@Override
	public MasterAnchoreWhitelist findById(long pId) {
		return masterAnchoreWhitelistRepository.findById(pId).orElse(null);
	}
	
	@Override
	public void update(MasterAnchoreWhitelist masterAnchoreWhitelist) {
		masterAnchoreWhitelistRepository.save(masterAnchoreWhitelist);
	}
	
	@Override
	public void delete(MasterAnchoreWhitelist masterAnchoreWhitelist) {
		masterAnchoreWhitelistRepository.delete(masterAnchoreWhitelist);
	}

	@Override
	public void deleteById(long pId) {
		masterAnchoreWhitelistRepository.deleteById(pId);
	}

}
