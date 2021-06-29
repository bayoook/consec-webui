package co.id.btpn.web.containerMonitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.id.btpn.web.containerMonitoring.model.PolicyAnchore;


/**
 *
 * @author Ferry Fadly
 */
@Repository("policyAnchoreRepository")
public interface PolicyAnchoreRepository extends JpaRepository<PolicyAnchore, Long> {
	 
}
