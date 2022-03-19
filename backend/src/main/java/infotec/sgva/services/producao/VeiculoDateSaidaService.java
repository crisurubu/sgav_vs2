package infotec.sgva.services.producao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import infotec.sgva.dto.producao.VeiculoDateSaidaDTO;
import infotec.sgva.entities.producao.VeiculoDateSaida;
import infotec.sgva.exception.RegraNegocioException;
import infotec.sgva.repository.producao.VeiculoDateSaidaRepository;

@Service
public class VeiculoDateSaidaService {
	
	
	
	@Autowired
	private VeiculoDateSaidaRepository repository;
	
	
	@Transactional(readOnly = true)
	public Page<VeiculoDateSaidaDTO> findAll(Pageable pegeable){
		Page<VeiculoDateSaida> result = repository.findAll(pegeable);
		Page<VeiculoDateSaidaDTO> page = result.map(x -> VeiculoDateSaidaDTO.convert(x));
		return page;
	}
	
	
	@Transactional
	public VeiculoDateSaida save(VeiculoDateSaida VeiculoDateSaida)  {
		validar(VeiculoDateSaida);
		return repository.save(VeiculoDateSaida);
		
	}
	
	
	
	public void validar(VeiculoDateSaida VeiculoDateSaida) {
		if(VeiculoDateSaida.getDataEntrada() == null) {
			throw new RegraNegocioException("Informe Uma data de Saída válida (dd/MM/yyyy HH:mm:ss).");
		}
		if(VeiculoDateSaida.getId().getFuncionario() == null || VeiculoDateSaida.getId().getFuncionario().getEmail().trim().equals("") ) {
			throw new RegraNegocioException("Informe um email válido");
		}
		if(VeiculoDateSaida.getId().getVeiculo() == null || VeiculoDateSaida.getId().getVeiculo().getChassi().trim().equals("") ) {
			throw new RegraNegocioException("Informe um chassis válido");
		}
			
	}
	
	
	

}
