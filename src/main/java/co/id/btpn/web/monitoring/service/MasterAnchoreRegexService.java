package co.id.btpn.web.monitoring.service;

import java.util.List;

import co.id.btpn.web.monitoring.model.MasterAnchoreRegex;

public interface MasterAnchoreRegexService {
 
    void save(MasterAnchoreRegex masterAnchoreRegex);

	List<MasterAnchoreRegex> findAll() ;

	MasterAnchoreRegex findById(long pId);
	
	void update(MasterAnchoreRegex masterAnchoreRegex);
	
	void delete(MasterAnchoreRegex masterAnchoreRegex);

	void deleteById(long id);
    
}
