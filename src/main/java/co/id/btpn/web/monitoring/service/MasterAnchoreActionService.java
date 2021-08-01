package co.id.btpn.web.monitoring.service;

import java.util.List;

import co.id.btpn.web.monitoring.model.MasterAnchoreAction;

public interface MasterAnchoreActionService {
    
    void save(MasterAnchoreAction masterAnchoreAction);

	List<MasterAnchoreAction> findAll() ;

	MasterAnchoreAction findById(long pId);
	
	void update(MasterAnchoreAction masterAnchoreAction);
	
	void delete(MasterAnchoreAction masterAnchoreAction);

	void deleteById(long id);

}
