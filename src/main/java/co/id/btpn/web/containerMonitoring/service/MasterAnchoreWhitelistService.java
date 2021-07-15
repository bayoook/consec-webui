package co.id.btpn.web.containerMonitoring.service;

import java.util.List;

import co.id.btpn.web.containerMonitoring.model.MasterAnchoreWhitelist;

public interface MasterAnchoreWhitelistService {
    
    void save(MasterAnchoreWhitelist masterAnchoreWhitelist);

	List<MasterAnchoreWhitelist> findAll() ;

	MasterAnchoreWhitelist findById(long pId);
	
	void update(MasterAnchoreWhitelist masterAnchoreWhitelist);
	
	void delete(MasterAnchoreWhitelist masterAnchoreWhitelist);

	void deleteById(long id);
    
}
