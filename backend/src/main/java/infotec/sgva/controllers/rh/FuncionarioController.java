package infotec.sgva.controllers.rh;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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

import infotec.sgva.dto.rh.FuncionarioDTO;
import infotec.sgva.entities.rh.Funcao;
import infotec.sgva.entities.rh.Funcionario;
import infotec.sgva.enums.FuncionarioStatus;
import infotec.sgva.exception.RegraNegocioException;
import infotec.sgva.services.rh.FuncaoService;
import infotec.sgva.services.rh.FuncionarioService;
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
	public ResponseEntity<?> findById(@PathVariable("id") Long id){		
		return service.obterPorId(id).map(funcionario -> new ResponseEntity<>(FuncionarioDTO.convert(funcionario), HttpStatus.OK))
				 .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

			
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

		}).orElseGet(() -> new ResponseEntity<>("Funcion??rio n??o encontrado.", HttpStatus.BAD_REQUEST));
	}
	
	
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delatar(@PathVariable("id") Long id){
		return service.obterPorId(id).map(entidade -> {
			service.deletar(entidade);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}).orElseGet(() -> new ResponseEntity<>("Funcion??rio n??o encontrado .", HttpStatus.BAD_REQUEST));
	}
	
	
	
	private Funcionario converter(FuncionarioDTO dto) throws ParseException {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(dto.getNome());
		funcionario.setEmail(dto.getEmail());
		funcionario.setCpf(dto.getCpf());
		funcionario.setCelular(dto.getCelular());
		funcionario.setDataAdmissao(sdf.parse(dto.getDataAdmissao()));
		
		
		Funcao funcao = funcaoService.obterPorId(dto.getFuncao().getId())
				.orElseThrow(() -> new RegraNegocioException("Fun????o n??o encontrada para Id Informado"));
		
		funcionario.setFuncao(funcao);
		
		if(dto.getStatus() != null) {
			funcionario.setStatus(FuncionarioStatus.valueOf(dto.getStatus()));
		}
		
		return funcionario;		
	}
	

}
