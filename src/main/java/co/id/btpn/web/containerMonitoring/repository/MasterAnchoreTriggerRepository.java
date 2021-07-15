package co.id.btpn.web.containerMonitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.id.btpn.web.containerMonitoring.model.MasterAnchoreTrigger;

@Repository("masterAnchoreTriggerRepository")
public interface MasterAnchoreTriggerRepository extends JpaRepository<MasterAnchoreTrigger, Long>  {
    
}
