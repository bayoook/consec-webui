package co.id.btpn.web.containerMonitoring.service;

import java.util.List;
import co.id.btpn.web.containerMonitoring.model.UserLog;

public interface UserLogService {

	List<UserLog> findAll() ;

	UserLog findById(long pId);

}
