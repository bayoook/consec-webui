package co.id.btpn.web.containerMonitoring.service;

import java.util.List;

import co.id.btpn.web.containerMonitoring.model.MasterFalcoSyscall;

public interface MasterFalcoSyscallService {
    
    void save(MasterFalcoSyscall masterFalcoSyscall);

	List<MasterFalcoSyscall> findAll() ;

	MasterFalcoSyscall findById(long pId);
	
	void update(MasterFalcoSyscall masterFalcoSyscall);
	
	void delete(MasterFalcoSyscall masterFalcoSyscall);

	void deleteById(long id);
    
}
