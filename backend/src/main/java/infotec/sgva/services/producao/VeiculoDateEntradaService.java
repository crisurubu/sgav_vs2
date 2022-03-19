package infotec.sgva.services.producao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import infotec.sgva.dto.producao.VeiculoDateEntradaDTO;
import infotec.sgva.entities.producao.VeiculoDateEntrada;
import infotec.sgva.exception.RegraNegocioException;
import infotec.sgva.repository.producao.VeiculoDateEntradaRepository;

@Service
public class VeiculoDateEntradaService {
	
	
	
	@Autowired
	private VeiculoDateEntradaRepository repository;
	
	
	@Transactional(readOnly = true)
	public Page<VeiculoDateEntradaDTO> findAll(Pageable pegeable){
		Page<VeiculoDateEntrada> result = repository.findAll(pegeable);
		Page<VeiculoDateEntradaDTO> page = result.map(x -> VeiculoDateEntradaDTO.convert(x));
		return page;
	}
	
	
	@Transactional
	public VeiculoDateEntrada save(VeiculoDateEntrada veiculoDateEntrada)  {
		validar(veiculoDateEntrada);
		return repository.save(veiculoDateEntrada);
		
	}
	
	
	
	public void validar(VeiculoDateEntrada veiculoDateEntrada) {
		if(veiculoDateEntrada.getDataEntrada() == null) {
			throw new RegraNegocioException("Informe Uma data de entrada válida (dd/MM/yyyy HH:mm:ss).");
		}
		if(veiculoDateEntrada.getId().getFuncionario() == null || veiculoDateEntrada.getId().getFuncionario().getEmail().trim().equals("") ) {
			throw new RegraNegocioException("Informe um email válido");
		}
		if(veiculoDateEntrada.getId().getVeiculo() == null || veiculoDateEntrada.getId().getVeiculo().getChassi().trim().equals("") ) {
			throw new RegraNegocioException("Informe um chassis válido");
		}
			
	}
	
	
	

}
