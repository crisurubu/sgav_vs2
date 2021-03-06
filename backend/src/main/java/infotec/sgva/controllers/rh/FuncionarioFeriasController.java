package infotec.sgva.controllers.rh;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import infotec.sgva.dto.rh.FuncionarioFeriasDTO;
import infotec.sgva.entities.rh.Funcionario;
import infotec.sgva.entities.rh.FuncionarioFerias;
import infotec.sgva.enums.FuncionarioStatus;
import infotec.sgva.exception.RegraNegocioException;
import infotec.sgva.services.rh.FuncionarioFeriasService;
import infotec.sgva.services.rh.FuncionarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/funcionarios/ferias")
public class FuncionarioFeriasController {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Autowired
	private FuncionarioFeriasService service;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody FuncionarioFeriasDTO dto) throws ParseException{
		try {
			
			FuncionarioFerias entity = converter(dto);
			
			entity = service.ferias(entity);
			return new ResponseEntity<>(entity, HttpStatus.CREATED);
			
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	private Funcionario funcionarioFerias(Long id){
		   
		Optional<Funcionario> entity = funcionarioService.obterPorId(id);
		FuncionarioStatus statusFuncionario = FuncionarioStatus.FERIAS;
		Funcionario funcionario = entity.get();		
		funcionario.setStatus(statusFuncionario);
		funcionarioService.atualizar(funcionario);
		return funcionario;
	}
	
	
	private FuncionarioFerias converter(FuncionarioFeriasDTO dto) throws ParseException {
		FuncionarioFerias funcionarioFerias = new FuncionarioFerias();
		funcionarioFerias.setId(dto.getId());
		funcionarioFerias.setDias(dto.getDias());
		funcionarioFerias.setValor(dto.getValor());
		funcionarioFerias.setDataInicio(sdf.parse(dto.getDataInicio()));
		
		Date dt = sdf.parse(dto.getDataInicio());	        
	    Calendar c = Calendar.getInstance();
	    c.setTime(dt);
	    c.add(Calendar.DATE, dto.getDias());
	    dt = c.getTime();		
		funcionarioFerias.setDataTermido(dt);
		
		Funcionario funcionario = funcionarioFerias(dto.getFuncionario().getId());
		if(funcionario == null) {
			new RegraNegocioException("Funcion??rio n??o encontrada para Id Informado");
		}		
		funcionarioFerias.setFuncionario(funcionario);
		
		return funcionarioFerias;	
		
	}

}
