package co.id.btpn.web.containerMonitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.id.btpn.web.containerMonitoring.model.MasterFalcoSyscall;

@Repository("masterFalcoSyscallRepository")
public interface MasterFalcoSyscallRepository extends JpaRepository<MasterFalcoSyscall, Long>  {
    
}
