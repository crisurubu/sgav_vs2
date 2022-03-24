package infotec.sgva.services.producao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import infotec.sgva.entities.producao.Apontamentos;
import infotec.sgva.entities.producao.ApontamentosPK;
import infotec.sgva.entities.producao.Veiculo;
import infotec.sgva.exception.RegraNegocioException;
import infotec.sgva.repository.producao.ApontamentosRepository;
import infotec.sgva.repository.producao.VeiculoRepository;

@Service
public class ApontamentosService {
	
	@Autowired
	private ApontamentosRepository repository;
	
	@Autowired
	private VeiculoRepository veiculoRepository;
	
	
	@Transactional(readOnly = true)
	public Optional<Apontamentos>findByPorChassi(String chassi) {
		
		Optional<Veiculo> buscaVeiculo = veiculoRepository.findByChassi(chassi);
		Veiculo veiculo = buscaVeiculo.get();
		
		Apontamentos apontamentos = new Apontamentos();
		apontamentos.setVeiculo(veiculo);
		
		ApontamentosPK id = apontamentos.getId();
		
		
		Optional<Apontamentos> result = repository.buscarApontamentoPorIdVeiculo(id);		
		return result;
		
	}
	
	@Transactional
	public Apontamentos salvar(Apontamentos apontamento) {
		validar(apontamento);		
		return repository.save(apontamento);
	}
	
	
	
	public void validar(Apontamentos apontamento) {
		
		if(apontamento.getId().getVeiculo() == null || apontamento.getId().getVeiculo().getChassi().trim().equals("") ) {
			throw new RegraNegocioException("Informe um chassis válido.");
		}
		if(apontamento.getApontamento() == null || apontamento.getApontamento().trim().equals("")) {
			throw new RegraNegocioException("Informe apontamento.");
		}
		if(apontamento.getStatus() == null) {
			throw new RegraNegocioException("Informe status.");
		}
			
	}
	

}
