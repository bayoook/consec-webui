package co.id.btpn.web.containerMonitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.id.btpn.web.containerMonitoring.model.RuleFalco;


/**
 *
 * @author Ferry Fadly
 */
@Repository("ruleFalcoRepository")
public interface RuleFalcoRepository extends JpaRepository<RuleFalco, Long> {
	 
}
