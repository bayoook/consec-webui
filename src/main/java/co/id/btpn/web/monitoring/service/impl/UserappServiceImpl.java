package co.id.btpn.web.monitoring.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.btpn.web.monitoring.model.UserLog;
import co.id.btpn.web.monitoring.model.Userapp;
import co.id.btpn.web.monitoring.repository.UserLogRepository;
import co.id.btpn.web.monitoring.repository.UserappRepository;
import co.id.btpn.web.monitoring.service.UserappService;
import co.id.btpn.web.monitoring.util.Util;



@Service("userappService")
public class UserappServiceImpl implements UserappService{

	@Autowired
	private UserappRepository userappRepository;

	@Autowired
	private UserLogRepository userLogRepository;

	@Autowired
	private Util util;
	
	@Override
	public void save(Userapp user) {
		
        user.setActive(1);
		user.setCreatedBy(util.getLoggedUserName());
		user.setCreatedDate(new Date());

		UserLog userLog = new UserLog();
		userLog.setActivity("Add User \""+user.getName() +"\" ");
		userLog.setLogDate(new Date());
		userLog.setName(util.getLoggedUserName());
		userLogRepository.save(userLog);
		
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
		userapp.setModifiedBy(util.getLoggedUserName());
		userapp.setModifiedDate(new Date());

		UserLog userLog = new UserLog();
		userLog.setActivity("Update User \""+userapp.getName() +"\" ");
		userLog.setLogDate(new Date());
		userLog.setName(util.getLoggedUserName());
		userLogRepository.save(userLog);

		userappRepository.save(userapp);
	}
	
	@Override
	public void deactive(Userapp userapp) {
		userapp.setActive(0);
		userapp.setModifiedBy(util.getLoggedUserName());
		userapp.setModifiedDate(new Date());

		UserLog userLog = new UserLog();
		userLog.setActivity("Deactivated User \""+userapp.getName() +"\" ");
		userLog.setLogDate(new Date());
		userLog.setName(util.getLoggedUserName());
		userLogRepository.save(userLog);

		userappRepository.save(userapp);
	}

	@Override
	public void deactiveById(long pId) {
		Userapp userapp = userappRepository.findById(pId).orElse(null);
		userapp.setActive(0);
		userapp.setModifiedBy(util.getLoggedUserName());
		userapp.setModifiedDate(new Date());

		UserLog userLog = new UserLog();
		userLog.setActivity("Deactivated User \""+userapp.getName() +"\" ");
		userLog.setLogDate(new Date());
		userLog.setName(util.getLoggedUserName());
		userLogRepository.save(userLog);

		userappRepository.save(userapp);
	}

	@Override
	public List<Userapp> findByName(String name){
		return userappRepository.findByName(name);
	}

}
