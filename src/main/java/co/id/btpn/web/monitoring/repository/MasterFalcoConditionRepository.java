package co.id.btpn.web.monitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.id.btpn.web.monitoring.model.MasterFalcoCondition;

@Repository("masterFalcoConditionRepository")
public interface MasterFalcoConditionRepository extends JpaRepository<MasterFalcoCondition, Long>  {
    
}
