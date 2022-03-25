package infotec.sgva.services.rh;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import infotec.sgva.dto.rh.FuncionarioDTO;
import infotec.sgva.entities.rh.Funcionario;
import infotec.sgva.enums.FuncionarioStatus;
import infotec.sgva.exception.RegraNegocioException;
import infotec.sgva.repository.rh.FuncionarioRepository;

@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository repository;
	
	@Transactional(readOnly = true)
	public Page<FuncionarioDTO> findAll(Pageable pageable){
		Page<Funcionario> result = repository.findAll(pageable);
		Page<FuncionarioDTO> page = result.map(x -> FuncionarioDTO.convert(x));
		return page;
	}
	
	@Transactional(readOnly = true)
	public FuncionarioDTO findById(Long id) {
		Funcionario result = repository.findById(id).get();
		FuncionarioDTO dto = FuncionarioDTO.convert(result);
		return dto;
	}
	
	@Transactional
	public Funcionario salvar(Funcionario funcionario) {
		validar(funcionario);
		funcionario.setStatus(FuncionarioStatus.ATIVO);
		return repository.save(funcionario);		
		
	}
	
	@Transactional
	public Funcionario atualizar(Funcionario funcionario) {
		Objects.requireNonNull(funcionario.getId());
		validar(funcionario);
		return repository.save(funcionario);		
		
	}
	
	@Transactional
	public Funcionario ferias(Funcionario funcionario) {
		Objects.requireNonNull(funcionario.getId());
		validar(funcionario);
		return funcionario;
	}
	
	@Transactional
	public Funcionario afastar(Funcionario funcionario) {
		Objects.requireNonNull(funcionario.getId());
		validar(funcionario);
		return funcionario;
	}
	
	@Transactional
	public void deletar(Funcionario funcionario) {
		Objects.requireNonNull(funcionario.getId());
		repository.delete(funcionario);		
		
	}
	
	public void atualizaStatus(Funcionario funcionario , FuncionarioStatus status) {
		funcionario.setStatus(status);
		atualizar(funcionario);
	}
	

	public void validar(Funcionario funcionario) {
		if(funcionario.getNome() == null || funcionario.getNome().trim().equals("")) {
			throw new RegraNegocioException("Informe um nome válido");
		}
		if(funcionario.getEmail() == null || funcionario.getEmail().trim().equals("")) {
			throw new RegraNegocioException("Informe um email válido");
		}
		if(funcionario.getCelular() == null || funcionario.getCelular().trim().equals("")) {
			throw new RegraNegocioException("Informe um celular válido");
		}
		if(funcionario.getCpf() == null || funcionario.getCpf().trim().equals("")) {
			throw new RegraNegocioException("Informe um cpf válido");
		}
		
		if(funcionario.getFuncao() == null || funcionario.getFuncao().getId() == null) {
			throw new RegraNegocioException("Informe a função");
		}
				
		
		
	}
	public void validarCpf(String cpf) {
		boolean existe = repository.existsByCpf(cpf);
		if(existe) {
			throw new RegraNegocioException("Já existe o cpf: "+cpf+" cadastrado no sistema..");
		}
		
	}
	
	public Optional<Funcionario> obterPorId(Long id) {
		return repository.findById(id);
	}
	
	public Optional<Funcionario> obterPorCpf(String cpf) {
		return repository.findByCpf(cpf);
	}
	
	public Optional<Funcionario> obterPorEmail(String email) {
		return repository.findByEmail(email);
	}
	
	
	
	
	
	

}
