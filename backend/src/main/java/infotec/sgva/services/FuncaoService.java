package infotec.sgva.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import infotec.sgva.dto.FuncaoDTO;
import infotec.sgva.entities.Funcao;
import infotec.sgva.repository.FuncaoRepository;

@Service
public class FuncaoService {
	
	@Autowired
	private FuncaoRepository repository;
	
	@Transactional(readOnly = true)
	public Page<FuncaoDTO> findAll(Pageable pageable){
		Page<Funcao> result = repository.findAll(pageable);
		Page<FuncaoDTO> page = result.map(x -> FuncaoDTO.convert(x));
		return page;
	}
	
	@Transactional(readOnly = true)
	public FuncaoDTO findById(Long id) {
		Funcao result = repository.findById(id).get();
		FuncaoDTO dto = FuncaoDTO.convert(result);
		return dto;
	}
	
	public Optional<Funcao> obterPorId(Long id) {
		return repository.findById(id);
	}
	

}
