package co.id.btpn.web.monitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.id.btpn.web.monitoring.model.MasterFalcoSyscall;

@Repository("masterFalcoSyscallRepository")
public interface MasterFalcoSyscallRepository extends JpaRepository<MasterFalcoSyscall, Long>  {
    
}
