package co.id.btpn.web.containerMonitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.id.btpn.web.containerMonitoring.model.UserLog;


/**
 *
 * @author Ferry Fadly
 */
@Repository("userLogRepository")
public interface UserLogRepository extends JpaRepository<UserLog, Long> {

}
