package co.id.btpn.web.monitoring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.btpn.web.monitoring.model.MasterFalcoSyscall;
import co.id.btpn.web.monitoring.repository.MasterFalcoSyscallRepository;
import co.id.btpn.web.monitoring.service.MasterFalcoSyscallService;



@Service("masterFalcoSyscallService")
public class MasterFalcoSyscallServiceImpl implements MasterFalcoSyscallService{

	@Autowired
	private MasterFalcoSyscallRepository masterFalcoSyscallRepository;
	

	@Override
	public void save(MasterFalcoSyscall masterFalcoSyscall) {
		masterFalcoSyscallRepository.save(masterFalcoSyscall);
	}

	@Override
	public List<MasterFalcoSyscall> findAll() {
		return  masterFalcoSyscallRepository.findAll();
	}

	@Override
	public MasterFalcoSyscall findById(long pId) {
		return masterFalcoSyscallRepository.findById(pId).orElse(null);
	}
	
	@Override
	public void update(MasterFalcoSyscall masterFalcoSyscall) {
		masterFalcoSyscallRepository.save(masterFalcoSyscall);
	}
	
	@Override
	public void delete(MasterFalcoSyscall masterFalcoSyscall) {
		masterFalcoSyscallRepository.delete(masterFalcoSyscall);
	}

	@Override
	public void deleteById(long pId) {
		masterFalcoSyscallRepository.deleteById(pId);
	}

}
