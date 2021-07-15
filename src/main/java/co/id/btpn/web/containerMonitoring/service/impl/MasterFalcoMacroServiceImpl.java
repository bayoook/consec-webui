package co.id.btpn.web.containerMonitoring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.btpn.web.containerMonitoring.model.MasterFalcoMacro;
import co.id.btpn.web.containerMonitoring.repository.MasterFalcoMacroRepository;
import co.id.btpn.web.containerMonitoring.service.MasterFalcoMacroService;



@Service("masterFalcoMacroService")
public class MasterFalcoMacroServiceImpl implements MasterFalcoMacroService{

	@Autowired
	private MasterFalcoMacroRepository masterFalcoMacroRepository;
	

	@Override
	public void save(MasterFalcoMacro masterFalcoMacro) {
		masterFalcoMacroRepository.save(masterFalcoMacro);
	}

	@Override
	public List<MasterFalcoMacro> findAll() {
		return  masterFalcoMacroRepository.findAll();
	}

	@Override
	public MasterFalcoMacro findById(long pId) {
		return masterFalcoMacroRepository.findById(pId).orElse(null);
	}
	
	@Override
	public void update(MasterFalcoMacro masterFalcoMacro) {
		masterFalcoMacroRepository.save(masterFalcoMacro);
	}
	
	@Override
	public void delete(MasterFalcoMacro masterFalcoMacro) {
		masterFalcoMacroRepository.delete(masterFalcoMacro);
	}

	@Override
	public void deleteById(long pId) {
		masterFalcoMacroRepository.deleteById(pId);
	}

}
