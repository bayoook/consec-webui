package co.id.btpn.web.containerMonitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.id.btpn.web.containerMonitoring.model.PolicyFalco;


/**
 *
 * @author Ferry Fadly
 */
@Repository("policyFalcoRepository")
public interface PolicyFalcoRepository extends JpaRepository<PolicyFalco, Long> {
	 
}
