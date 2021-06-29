package co.id.btpn.web.containerMonitoring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.btpn.web.containerMonitoring.model.Userapp;
import co.id.btpn.web.containerMonitoring.repository.UserappRepository;
import co.id.btpn.web.containerMonitoring.service.UserappService;



@Service("userappService")
public class UserappServiceImpl implements UserappService{

	@Autowired
	private UserappRepository userappRepository;
	
   

	@Override
	public void save(Userapp user) {
        user.setActive(1);
        // Role userRole = roleRepository.findByRole("ADMIN");
        // user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userappRepository.save(user);
	}

	@Override
	public List<Userapp> findAll() {
		return  userappRepository.findAll();
	}

	@Override
	public Userapp findById(long pId) {
		// TODO Auto-generated method stub
		return userappRepository.findById(pId).orElse(null);
	}
	
	@Override
	public void update(Userapp userapp) {

		//updatePageSlugWithTitle(p1);
		userappRepository.save(userapp);
	}
	
	@Override
	public void deactive(Userapp userapp) {
		userapp.setActive(0);
		userappRepository.save(userapp);
	}

	@Override
	public void deactiveById(long pId) {
		Userapp userapp = userappRepository.findById(pId).orElse(null);
		userapp.setActive(0);
		userappRepository.save(userapp);
	}

	@Override
	public List<Userapp> findByName(String name){
		return userappRepository.findByName(name);
	}

}
