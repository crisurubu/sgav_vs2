package infotec.sgva.services.estoque;

import java.util.Objects;

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
	public FornecedoresDTO findByCnpj(String cnpj) {
		Fornecedores buscaFornecedor = repository.findByCnpj(cnpj).get();
		FornecedoresDTO dto = FornecedoresDTO.convert(buscaFornecedor);
		return dto;
		
	}
	
	@Transactional(readOnly = true)
	public Page<FornecedoresDTO> findAll(Pageable pageable) {
		Page<Fornecedores> result = repository.findAll(pageable);
		Page<FornecedoresDTO> page = result.map(x -> FornecedoresDTO.convert(x));		
		return page;
		
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
