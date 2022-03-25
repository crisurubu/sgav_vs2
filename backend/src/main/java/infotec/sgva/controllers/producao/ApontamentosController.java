package infotec.sgva.controllers.producao;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import infotec.sgva.dto.producao.ApontamentosDTO;
import infotec.sgva.entities.producao.Apontamentos;
import infotec.sgva.entities.producao.Veiculo;
import infotec.sgva.enums.ApontamentosStatus;
import infotec.sgva.exception.RegraNegocioException;
import infotec.sgva.services.producao.ApontamentosService;
import infotec.sgva.services.producao.VeiculoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/apontamentos")
public class ApontamentosController {
	
	@Autowired
	private VeiculoService VeiculoService;
	
	
	
	@Autowired
	private ApontamentosService service;
	
	
	@GetMapping(value = "/{chassi}")
	public Apontamentos findById(@PathVariable("chassi") String chassi){		
		Optional<Apontamentos> result =  service.findByPorChassi(chassi);
		Apontamentos dto = result.get();
		return dto;
				
							
	}
	
	@PostMapping
	public ResponseEntity<?>salvar(@RequestBody ApontamentosDTO dto) throws ParseException{
		try {
			
			Apontamentos entidade = converter(dto);	
			entidade.setStatus(ApontamentosStatus.ABERTO);
			entidade = service.salvar(entidade);			
			return new ResponseEntity<>(entidade, HttpStatus.CREATED);
			
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	
	private Apontamentos converter (ApontamentosDTO dto) {
		Apontamentos apontamentos = new Apontamentos();
		
		apontamentos.setApontamento(dto.getApontamento());
		
		Veiculo veiculo = VeiculoService.findByChassi(dto.getChassi())
				.orElseThrow(() -> new RegraNegocioException("Veículo não encontrado.."));
		apontamentos.setVeiculo(veiculo);
		
		if(dto.getStatus() != null) {
			apontamentos.setStatus(ApontamentosStatus.valueOf(dto.getStatus()));
		}
		
		Set<Apontamentos> lista = new HashSet<>();
		lista.add(apontamentos);
		
		return apontamentos;	
		
		
	}
}
