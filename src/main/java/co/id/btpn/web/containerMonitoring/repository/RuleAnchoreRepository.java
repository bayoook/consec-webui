package co.id.btpn.web.containerMonitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.id.btpn.web.containerMonitoring.model.RuleAnchore;


/**
 *
 * @author Ferry Fadly
 */
@Repository("ruleAnchoreRepository")
public interface RuleAnchoreRepository extends JpaRepository<RuleAnchore, Long> {
	 
}
