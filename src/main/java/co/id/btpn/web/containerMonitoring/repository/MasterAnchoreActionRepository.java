package co.id.btpn.web.containerMonitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.id.btpn.web.containerMonitoring.model.MasterAnchoreAction;

@Repository("masterAnchoreActionRepository")
public interface MasterAnchoreActionRepository extends JpaRepository<MasterAnchoreAction, Long>  {
    
}
