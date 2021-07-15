package co.id.btpn.web.containerMonitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.id.btpn.web.containerMonitoring.model.MasterAnchoreWhitelist;

@Repository("masterAnchoreWhitelistRepository")
public interface MasterAnchoreWhitelistRepository extends JpaRepository<MasterAnchoreWhitelist, Long>  {
    
}
