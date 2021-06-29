package co.id.btpn.web.containerMonitoring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.btpn.web.containerMonitoring.model.Role;
import co.id.btpn.web.containerMonitoring.repository.RoleRepository;
import co.id.btpn.web.containerMonitoring.service.RoleService;



@Service("roleService")
public class RoleServiceImpl implements RoleService{

	@Autowired
    private RoleRepository roleRepository;
   

	@Override
	public void save(Role role) {
        // user.setActive(1);
        // Role userRole = roleRepository.findByRole("ADMIN");
        // user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		roleRepository.save(role);
	}

	@Override
	public List<Role> findAll() {
		return  roleRepository.findAll();
	}

	@Override
	public Role findById(long pId) {
		// TODO Auto-generated method stub
		return roleRepository.findById(pId).orElse(null);
	}
	
	@Override
	public void update(Role role) {

		//updatePageSlugWithTitle(p1);
		roleRepository.save(role);
	}

}
