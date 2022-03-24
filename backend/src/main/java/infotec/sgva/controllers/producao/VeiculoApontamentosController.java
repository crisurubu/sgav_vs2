package infotec.sgva.controllers.producao;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import infotec.sgva.dto.producao.ApontamentosDTO;
import infotec.sgva.dto.producao.VeiculoApontamentosDTO;
import infotec.sgva.entities.producao.Apontamentos;
import infotec.sgva.entities.producao.Veiculo;
import infotec.sgva.entities.producao.VeiculoApontamentos;
import infotec.sgva.enums.ApontamentosStatus;
import infotec.sgva.exception.RegraNegocioException;
import infotec.sgva.services.producao.ApontamentosService;
import infotec.sgva.services.producao.VeiculoApontamentosService;
import infotec.sgva.services.producao.VeiculoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/veiculoapontamentos")
public class VeiculoApontamentosController {
	
	@Autowired
	private final ApontamentosService apontamentosService;
	
	@Autowired
	private final VeiculoApontamentosService service;
	
	@Autowired
	private final VeiculoService veiculoService;
	
	
	
	@GetMapping
	public Page<VeiculoApontamentosDTO> findAll(Pageable pegeable){
		return service.findAll(pegeable);
	}
	
	@PostMapping
	public ResponseEntity<?>salvar(@RequestBody VeiculoApontamentosDTO dto, ApontamentosDTO apontamentosDTO) throws ParseException{
		try {
			
			VeiculoApontamentos entidade = converter(apontamentosDTO, dto);				
			entidade = service.salvar(entidade);			
			return new ResponseEntity<>(entidade, HttpStatus.CREATED);
			
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	
	
	private VeiculoApontamentos converter (ApontamentosDTO apontamentosDTO, VeiculoApontamentosDTO dto)  {
		Apontamentos apontamentos = new Apontamentos();		
		apontamentos.setApontamento(apontamentosDTO.getApontamento());
		
		Veiculo veiculo = veiculoService.findByChassi(apontamentosDTO.getChassi())
				.orElseThrow(() -> new RegraNegocioException("Veículo não encontrado.."));
		apontamentos.setVeiculo(veiculo);
		
		if(apontamentosDTO.getStatus() != null) {
			apontamentos.setStatus(ApontamentosStatus.valueOf(apontamentosDTO.getStatus()));
		}
		apontamentosService.salvar(apontamentos);
		
		VeiculoApontamentos veiculoApontamentos = new VeiculoApontamentos();
		service.salvar(veiculoApontamentos);
		
		for(Apontamentos a : veiculoApontamentos.getApontamentos()) {
			   a.getApontamento();
			   
		}
		
		return veiculoApontamentos;
		
		
	}

}
