package co.id.btpn.web.monitoring.service;

import java.util.List;

import co.id.btpn.web.monitoring.model.MasterAnchoreGate;

public interface MasterAnchoreGateService {

    void save(MasterAnchoreGate masterAnchoreGate);

	List<MasterAnchoreGate> findAll() ;

	MasterAnchoreGate findById(long pId);
	
	void update(MasterAnchoreGate masterAnchoreGate);
	
	void delete(MasterAnchoreGate masterAnchoreGate);

	void deleteById(long id);

}

