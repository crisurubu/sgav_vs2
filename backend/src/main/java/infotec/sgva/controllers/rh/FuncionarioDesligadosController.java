package infotec.sgva.controllers.rh;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import infotec.sgva.dto.rh.FuncionarioDesligadosDTO;
import infotec.sgva.entities.rh.Funcionario;
import infotec.sgva.entities.rh.FuncionarioDesligados;
import infotec.sgva.enums.FuncionarioStatus;
import infotec.sgva.enums.MotivoDesligamento;
import infotec.sgva.exception.RegraNegocioException;
import infotec.sgva.services.rh.FuncionarioDesligadosService;
import infotec.sgva.services.rh.FuncionarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/funcionarios")
public class FuncionarioDesligadosController {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Autowired
	private FuncionarioDesligadosService service;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@PutMapping(value = "/demitir")
	public ResponseEntity<?> demitir(@RequestBody FuncionarioDesligadosDTO dto) throws ParseException{
		try {
			
			FuncionarioDesligados funcionarioDesligados = converter(dto);
			funcionarioDesligados = service.demitir(funcionarioDesligados);
			return new ResponseEntity<>(funcionarioDesligados, HttpStatus.CREATED);
			
			
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		
	}
	
	
	private Funcionario funcionarioDemitir(Long id){
		   
		Optional<Funcionario> entity = funcionarioService.obterPorId(id);
		FuncionarioStatus statusFuncionario = FuncionarioStatus.INATIVO;
		Funcionario funcionario = entity.get();		
		funcionario.setStatus(statusFuncionario);
		funcionarioService.atualizar(funcionario);
		return funcionario;
	}
	
	private FuncionarioDesligados converter(FuncionarioDesligadosDTO dto) throws ParseException {
		FuncionarioDesligados desligado = new FuncionarioDesligados();
		desligado.setId(dto.getId());
		desligado.setDescricao(dto.getDescricao());		
		desligado.setDataDemissao(sdf.parse(dto.getDataDemissao()));
		
		
		Funcionario funcionario = funcionarioDemitir(dto.getFuncionario().getId());
		if(funcionario == null) {
			new RegraNegocioException("Funcionário não encontrada para Id Informado");
		}		
		desligado.setFuncionario(funcionario);
		
		if(dto.getDesligamento() != null) {
			desligado.setDesligamento(MotivoDesligamento.valueOf(dto.getDesligamento()));
		}
		
		return desligado;		
	}
					
					
				

		
	


}
