package infotec.sgva.services.estoque;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import infotec.sgva.dto.estoque.FornecedoresDTO;
import infotec.sgva.entities.estoque.Fornecedores;
import infotec.sgva.exception.RegraNegocioException;
import infotec.sgva.repository.estoque.FornecedoresRepository;

@Service
public class FornecedoresService {
	
	@Autowired
	private FornecedoresRepository repository;
	
	@Transactional(readOnly = true)
	public FornecedoresDTO findByNome(String nome) {
		Fornecedores buscaFornecedor = repository.findByNome(nome).get();
		FornecedoresDTO dto = FornecedoresDTO.convert(buscaFornecedor);
		return dto;
		
	}
	
	@Transactional(readOnly = true)
	public FornecedoresDTO findById(Long id) {
		Optional<Fornecedores> busca = repository.findById(id);
		if(busca.isEmpty()) {
			throw new RegraNegocioException("Não foi encontrado Fornecedor pelo id informado");
		}
		Fornecedores newFornecedor = busca.get();
		FornecedoresDTO dto = FornecedoresDTO.convert(newFornecedor);
		return dto;
		
	}
	
	@Transactional(readOnly = true)
	public Page<FornecedoresDTO> findAll(Pageable pageable) {
		Page<Fornecedores> result = repository.findAll(pageable);
		Page<FornecedoresDTO> page = result.map(x -> FornecedoresDTO.convert(x));		
		return page;
		
	}
	
	@Transactional
	public Optional<Fornecedores> buscaPorId(Long id){
		return repository.findById(id);
	}
	
	
	@Transactional
	public Fornecedores save(Fornecedores fornecedor) {
		validar(fornecedor);
		return repository.save(fornecedor);
	}
	
	@Transactional
	public Fornecedores atualizar(Fornecedores fornecedor) {
		Objects.requireNonNull(fornecedor.getId());
		validar(fornecedor);
		return repository.saveAndFlush(fornecedor);		
		
	}
	
	
	public void validar (Fornecedores fornecedor) {
		if(fornecedor.getCnpj() == null || fornecedor.getCnpj().trim().equals("")) {
			throw new RegraNegocioException("Informe um cnpj válido");
		}
		if(fornecedor.getEmail() == null || fornecedor.getEmail().trim().equals("")) {
			throw new RegraNegocioException("Informe um email válido");
		}
		if(fornecedor.getFone() == null || fornecedor.getFone().trim().equals("")) {
			throw new RegraNegocioException("Informe um telefone válido");
		}
		if(fornecedor.getNome() == null || fornecedor.getNome().trim().equals("")) {
			throw new RegraNegocioException("Informe um nome válido");
		}
	}

}
