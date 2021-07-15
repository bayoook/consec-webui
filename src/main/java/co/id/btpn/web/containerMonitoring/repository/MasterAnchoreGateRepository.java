package co.id.btpn.web.containerMonitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.id.btpn.web.containerMonitoring.model.MasterAnchoreGate;

@Repository("masterAnchoreGateRepository")
public interface MasterAnchoreGateRepository extends JpaRepository<MasterAnchoreGate, Long>  {
    
}
