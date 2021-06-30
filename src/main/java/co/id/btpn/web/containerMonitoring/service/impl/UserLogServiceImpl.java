package co.id.btpn.web.containerMonitoring.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.btpn.web.containerMonitoring.model.UserLog;
import co.id.btpn.web.containerMonitoring.model.Userapp;
import co.id.btpn.web.containerMonitoring.repository.UserLogRepository;
import co.id.btpn.web.containerMonitoring.repository.UserappRepository;
import co.id.btpn.web.containerMonitoring.service.UserLogService;
import co.id.btpn.web.containerMonitoring.util.Util;



@Service("userLogService")
public class UserLogServiceImpl implements UserLogService{

	@Autowired
	private UserLogRepository userLogRepository;

	
	@Override
	public List<UserLog> findAll() {
		return  userLogRepository.findAll();
	}

	@Override
	public UserLog findById(long pId) {
		// TODO Auto-generated method stub
		return userLogRepository.findById(pId).orElse(null);
	}
	

}
