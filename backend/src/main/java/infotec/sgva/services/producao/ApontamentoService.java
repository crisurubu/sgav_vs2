package infotec.sgva.services.producao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import infotec.sgva.entities.producao.Apontamento;
import infotec.sgva.entities.producao.Veiculo;
import infotec.sgva.exception.RegraNegocioException;
import infotec.sgva.repository.producao.ApontamentoRepository;
import infotec.sgva.repository.producao.VeiculoRepository;

@Service
public class ApontamentoService {
	
	@Autowired
	private ApontamentoRepository repository;
	
	@Autowired
	private VeiculoRepository veiculoRepository;
	
	
	@Transactional(readOnly = true)
	public List<Apontamento>findByPorChassi(String chassi) {
		
		Optional<Veiculo> buscaVeiculo = veiculoRepository.findByChassi(chassi);
		Veiculo veiculo = buscaVeiculo.get();
		
		Apontamento apontamentos = new Apontamento();
		apontamentos.setVeiculo(veiculo);
		
		Long id = apontamentos.getVeiculo().getId();
		
		
		List<Apontamento> result = repository.buscarApontamentoPorIdVeiculo(id);		
		return result;
		
	}
	
	@Transactional
	public Apontamento salvar(Apontamento apontamento) {
		validar(apontamento);		
		return repository.save(apontamento);
	}
	
	
	
	public void validar(Apontamento apontamento) {
		if(apontamento.getApontamento() == null || apontamento.getApontamento().trim().equals("")) {
			throw new RegraNegocioException("Informe apontamento.");
		}
		if(apontamento.getVeiculo() == null) {
			throw new RegraNegocioException("Informe Veículo.");
		}
		if(apontamento.getStatus() == null) {
			throw new RegraNegocioException("Informe status.");
		}
			
	}
	

}
