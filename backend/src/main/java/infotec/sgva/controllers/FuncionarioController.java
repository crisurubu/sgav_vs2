package infotec.sgva.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import infotec.sgva.dto.FuncionarioDTO;
import infotec.sgva.entities.Funcao;
import infotec.sgva.entities.Funcionario;
import infotec.sgva.enums.FuncionarioStatus;
import infotec.sgva.exception.RegraNegocioException;
import infotec.sgva.services.FuncaoService;
import infotec.sgva.services.FuncionarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/funcionarios")
public class FuncionarioController {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Autowired
	private FuncionarioService service;
	
	@Autowired
	private FuncaoService funcaoService;
	
	
	@GetMapping
	public Page<FuncionarioDTO> findAll(Pageable pegeable){
		return service.findAll(pegeable);
	}
	
	@GetMapping(value = "/{id}")
	public FuncionarioDTO findById(@PathVariable Long id){
		return service.findById(id);
				
	}
	
	@PostMapping
	public ResponseEntity<?>salvar(@RequestBody FuncionarioDTO dto) throws ParseException{
		try {
			
			Funcionario entidade = converter(dto);
			service.validarCpf(entidade.getCpf());
			entidade = service.salvar(entidade);
			
			return new ResponseEntity<>(entidade, HttpStatus.CREATED);
			
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> atualizar(@PathVariable("id") Long id, @RequestBody FuncionarioDTO dto){
		return service.obterPorId(id).map(entity ->{
			try 
				{
					Funcionario funcionario = converter(dto);
					funcionario.setId(entity.getId());
					service.atualizar(funcionario);
					return ResponseEntity.ok(funcionario);
					
					
				} 
			catch (RegraNegocioException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
			catch (ParseException e ) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}

		}).orElseGet(() -> new ResponseEntity<>("Funcionário não encontrado.", HttpStatus.BAD_REQUEST));
	}
	
	@PutMapping(value = "/{id}/demitir")
	public ResponseEntity<?> demitir(@PathVariable("id") Long id){
		return service.obterPorId(id).map(entity ->{
			FuncionarioStatus statusFuncionario = FuncionarioStatus.INATIVO;
			if(statusFuncionario == null) {
				return ResponseEntity.badRequest().body("Não foi possível atualizar status do funcionário..");
			}
			try 
				{
					
					entity.setDataDemissao(new Date());
					entity.setStatus(statusFuncionario);
					service.atualizar(entity);
					return ResponseEntity.ok(entity);
					
					
				} 
			catch (RegraNegocioException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
			

		}).orElseGet(() -> new ResponseEntity<>("Funcionário não encontrado.", HttpStatus.BAD_REQUEST));
	}
	
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delatar(@PathVariable("id") Long id){
		return service.obterPorId(id).map(entidade -> {
			service.deletar(entidade);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}).orElseGet(() -> new ResponseEntity<>("Funcionário não encontrado .", HttpStatus.BAD_REQUEST));
	}
	
	
	
	private Funcionario converter(FuncionarioDTO dto) throws ParseException {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(dto.getNome());
		funcionario.setEmail(dto.getEmail());
		funcionario.setCpf(dto.getCpf());
		funcionario.setCelular(dto.getCelular());
		funcionario.setDataAdmissao(sdf.parse(dto.getDataAdmissao()));
		funcionario.setDataDemissao(sdf.parse(dto.getDataDemissao()));
		
		Funcao funcao = funcaoService.obterPorId(dto.getFuncao().getId())
				.orElseThrow(() -> new RegraNegocioException("Função não encontrada para Id Informado"));
		
		funcionario.setFuncao(funcao);
		
		if(dto.getStatus() != null) {
			funcionario.setStatus(FuncionarioStatus.valueOf(dto.getStatus()));
		}
		
		return funcionario;		
	}
	

}
