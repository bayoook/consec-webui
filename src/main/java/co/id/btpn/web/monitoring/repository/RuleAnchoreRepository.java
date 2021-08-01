package co.id.btpn.web.monitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.id.btpn.web.monitoring.model.RuleAnchore;


/**
 *
 * @author Ferry Fadly
 */
@Repository("ruleAnchoreRepository")
public interface RuleAnchoreRepository extends JpaRepository<RuleAnchore, Long> {
	 
}
