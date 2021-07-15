package co.id.btpn.web.containerMonitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.id.btpn.web.containerMonitoring.model.MasterAnchorePolicyMapping;

@Repository("masterAnchorePolicyMappingRepository")
public interface MasterAnchorePolicyMappingRepository extends JpaRepository<MasterAnchorePolicyMapping, Long>  {
    
}
