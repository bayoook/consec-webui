package co.id.btpn.web.monitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.id.btpn.web.monitoring.model.RuleFalco;


/**
 *
 * @author Ferry Fadly
 */
@Repository("ruleFalcoRepository")
public interface RuleFalcoRepository extends JpaRepository<RuleFalco, Long> {
	 
}
