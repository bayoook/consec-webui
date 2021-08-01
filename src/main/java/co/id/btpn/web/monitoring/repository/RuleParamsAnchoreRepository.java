package co.id.btpn.web.monitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.id.btpn.web.monitoring.model.RuleParamsAnchore;


/**
 *
 * @author Ferry Fadly
 */
@Repository("ruleParamsAnchoreRepository")
public interface RuleParamsAnchoreRepository extends JpaRepository<RuleParamsAnchore, Long> {
	 
}
