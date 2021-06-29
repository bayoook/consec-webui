package co.id.btpn.web.containerMonitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.id.btpn.web.containerMonitoring.model.Role;



/**
 *
 * @author Ferry Fadly
 */
@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long>{
	Role findByRole(String role);

}
