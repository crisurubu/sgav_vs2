package infotec.sgva.services.producao;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import infotec.sgva.dto.producao.VeiculoDTO;
import infotec.sgva.entities.producao.Veiculo;
import infotec.sgva.enums.VeiculoStatus;
import infotec.sgva.exception.RegraNegocioException;
import infotec.sgva.repository.producao.VeiculoRepository;

@Service
public class VeiculoService {
	
	@Autowired
	private VeiculoRepository repository;
	
	@Transactional(readOnly = true)
	public Page<VeiculoDTO> findAll(Pageable pageable){
		Page<Veiculo> result = repository.findAll(pageable);
		Page<VeiculoDTO> page = result.map(x -> VeiculoDTO.converter(x));
		return page;
	}
	
	@Transactional(readOnly = true)
	public VeiculoDTO findById(Long id) {
		Veiculo result = repository.findById(id).get();
		VeiculoDTO dto = VeiculoDTO.converter(result);
		return dto;
	}
	
	@Transactional
	public Veiculo salvar(Veiculo Veiculo) {
		validar(Veiculo);
		Veiculo.setStatus(VeiculoStatus.PATIO);
		return repository.save(Veiculo);		
		
	}
	
	@Transactional
	public Veiculo atualizar(Veiculo veiculo) {
		Objects.requireNonNull(veiculo.getId());
		validar(veiculo);
		return repository.saveAndFlush(veiculo);		
		
	}
	
	public void validar(Veiculo Veiculo) {
		if(Veiculo.getChassi() == null || Veiculo.getChassi().trim().equals("")) {
			throw new RegraNegocioException("Informe um Chassi válido...");
		}
		if(Veiculo.getMarca() == null || Veiculo.getMarca().trim().equals("")) {
			throw new RegraNegocioException("Informe uma marca válido...");
		}
		
	}
	
	public void validarChassi(String chassi) {
		boolean existe = repository.existsByChassi(chassi);
		if(existe) {
			throw new RegraNegocioException("Já existe o Chassi: "+chassi+" cadastrado no sistema..");
		}
		
	}
	
	public Optional<Veiculo> findByChassi(String chassi){
		return repository.findByChassi(chassi);
		
	}
	
}
