package infotec.sgva.controllers.producao;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import infotec.sgva.dto.producao.ApontamentoDTO;
import infotec.sgva.entities.producao.Apontamento;
import infotec.sgva.entities.producao.Veiculo;
import infotec.sgva.enums.ApontamentosStatus;
import infotec.sgva.exception.RegraNegocioException;
import infotec.sgva.services.producao.ApontamentoService;
import infotec.sgva.services.producao.VeiculoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/apontamento")
public class ApontamentoController {
	
	@Autowired
	private VeiculoService VeiculoService;
	
	
	
	@Autowired
	private ApontamentoService service;
	
	@GetMapping(value = "/{chassi}")
	public List<Apontamento> findById(@PathVariable("chassi") String chassi){		
		List<Apontamento> result =  service.findByPorChassi(chassi);
		
		return result;
				
							
	}
	
	@PostMapping
	public ResponseEntity<?>salvar(@RequestBody ApontamentoDTO dto) throws ParseException{
		try {
			
			Apontamento entidade = converter(dto);	
			entidade.setStatus(ApontamentosStatus.ABERTO);
			entidade = service.salvar(entidade);			
			return new ResponseEntity<>(entidade, HttpStatus.CREATED);
			
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	
	private Apontamento converter (ApontamentoDTO dto) {
		Apontamento apontamentos = new Apontamento();
		
		apontamentos.setApontamento(dto.getApontamento());
		
		Veiculo veiculo = VeiculoService.findByChassi(dto.getChassi())
				.orElseThrow(() -> new RegraNegocioException("Veículo não encontrado.."));
		apontamentos.setVeiculo(veiculo);
		
		if(dto.getStatus() != null) {
			apontamentos.setStatus(ApontamentosStatus.valueOf(dto.getStatus()));
		}
		
		return apontamentos;	
		
		
	}

}
