package infotec.sgva.services.rh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import infotec.sgva.dto.rh.DepartamentoDTO;
import infotec.sgva.entities.rh.Departamento;
import infotec.sgva.repository.rh.DepartamentoRepository;

@Service
public class DepartamentoService {
	
	@Autowired
	private DepartamentoRepository repository;
	
	@Transactional(readOnly = true)
	public Page<DepartamentoDTO> findAll(Pageable pageable){
		Page<Departamento> result = repository.findAll(pageable);
		Page<DepartamentoDTO> page = result.map(x -> DepartamentoDTO.convert(x));
		return page;
	}
	
	@Transactional(readOnly = true)
	public DepartamentoDTO findById(Long id) {
		Departamento result = repository.findById(id).get();
		DepartamentoDTO dto = DepartamentoDTO.convert(result);
		return dto;
	}
	
	
	

}
