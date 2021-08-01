package co.id.btpn.web.monitoring.service;

import java.util.List;

import co.id.btpn.web.monitoring.model.MasterAnchoreWhitelist;

public interface MasterAnchoreWhitelistService {
    
    void save(MasterAnchoreWhitelist masterAnchoreWhitelist);

	List<MasterAnchoreWhitelist> findAll() ;

	MasterAnchoreWhitelist findById(long pId);
	
	void update(MasterAnchoreWhitelist masterAnchoreWhitelist);
	
	void delete(MasterAnchoreWhitelist masterAnchoreWhitelist);

	void deleteById(long id);
    
}
