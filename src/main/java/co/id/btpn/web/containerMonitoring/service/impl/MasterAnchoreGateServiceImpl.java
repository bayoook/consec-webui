package co.id.btpn.web.containerMonitoring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.btpn.web.containerMonitoring.model.MasterAnchoreGate;
import co.id.btpn.web.containerMonitoring.repository.MasterAnchoreGateRepository;
import co.id.btpn.web.containerMonitoring.service.MasterAnchoreGateService;



@Service("masterAnchoreGateService")
public class MasterAnchoreGateServiceImpl implements MasterAnchoreGateService{

	@Autowired
	private MasterAnchoreGateRepository masterAnchoreGateRepository;
	

	@Override
	public void save(MasterAnchoreGate masterAnchoreGate) {
		masterAnchoreGateRepository.save(masterAnchoreGate);
	}

	@Override
	public List<MasterAnchoreGate> findAll() {
		return  masterAnchoreGateRepository.findAll();
	}

	@Override
	public MasterAnchoreGate findById(long pId) {
		return masterAnchoreGateRepository.findById(pId).orElse(null);
	}
	
	@Override
	public void update(MasterAnchoreGate masterAnchoreGate) {
		masterAnchoreGateRepository.save(masterAnchoreGate);
	}
	
	@Override
	public void delete(MasterAnchoreGate masterAnchoreGate) {
		masterAnchoreGateRepository.delete(masterAnchoreGate);
	}

	@Override
	public void deleteById(long pId) {
		masterAnchoreGateRepository.deleteById(pId);
	}

}
