package co.id.btpn.web.containerMonitoring.service;

import java.util.List;

import co.id.btpn.web.containerMonitoring.model.Role;

public interface RoleService {

	void save(Role role);

	List<Role> findAll() ;

	Role findById(long pId);
	
	void update(Role role);

}
