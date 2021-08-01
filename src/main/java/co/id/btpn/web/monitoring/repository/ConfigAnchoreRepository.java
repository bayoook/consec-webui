package co.id.btpn.web.monitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.id.btpn.web.monitoring.model.ConfigAnchore;


/**
 *
 * @author Ferry Fadly
 */
@Repository("configAnchoreRepository")
public interface ConfigAnchoreRepository extends JpaRepository<ConfigAnchore, Long> {
	 
}
