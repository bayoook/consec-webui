package co.id.btpn.web.containerMonitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.id.btpn.web.containerMonitoring.model.MasterFalcoMacro;

@Repository("masterFalcoMacroRepository")
public interface MasterFalcoMacroRepository extends JpaRepository<MasterFalcoMacro, Long>  {
    
}
