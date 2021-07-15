package co.id.btpn.web.containerMonitoring.service;

import java.util.List;

import co.id.btpn.web.containerMonitoring.model.MasterAnchoreAction;

public interface MasterAnchoreActionService {
    
    void save(MasterAnchoreAction masterAnchoreAction);

	List<MasterAnchoreAction> findAll() ;

	MasterAnchoreAction findById(long pId);
	
	void update(MasterAnchoreAction masterAnchoreAction);
	
	void delete(MasterAnchoreAction masterAnchoreAction);

	void deleteById(long id);

}
