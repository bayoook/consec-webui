package co.id.btpn.web.containerMonitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.id.btpn.web.containerMonitoring.model.ConfigFalco;


/**
 *
 * @author Ferry Fadly
 */
@Repository("configFalcoRepository")
public interface ConfigFalcoRepository extends JpaRepository<ConfigFalco, Long> {
	 
}
