package infotec.sgva.services.producao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import infotec.sgva.dto.producao.VeiculoApontamentosDTO;
import infotec.sgva.entities.producao.VeiculoApontamentos;
import infotec.sgva.repository.producao.VeiculoApontamentosRepository;

@Service
public class VeiculoApontamentosService {
	
	@Autowired
	private VeiculoApontamentosRepository repository;
	
	
	@Transactional(readOnly = true)
	public Page<VeiculoApontamentosDTO> findAll(Pageable pageable){
		Page<VeiculoApontamentos> result =  repository.findAll(pageable);
		Page<VeiculoApontamentosDTO> page = result.map(x -> VeiculoApontamentosDTO.converter(x));
		return page;	
		
	}
	
	
	public VeiculoApontamentos salvar (VeiculoApontamentos veiculoApontamentos) {
		return repository.save(veiculoApontamentos);
	}
	
	
	

}
