package co.id.btpn.web.monitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.id.btpn.web.monitoring.model.MasterAnchoreGate;

@Repository("masterAnchoreGateRepository")
public interface MasterAnchoreGateRepository extends JpaRepository<MasterAnchoreGate, Long>  {
    
}
