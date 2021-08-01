package co.id.btpn.web.monitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.id.btpn.web.monitoring.model.MasterFalcoOperator;

@Repository("masterFalcoOperatorRepository")
public interface MasterFalcoOperatorRepository extends JpaRepository<MasterFalcoOperator, Long>  {
    
}
