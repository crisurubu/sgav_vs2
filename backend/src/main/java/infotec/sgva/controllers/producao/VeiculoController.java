package infotec.sgva.controllers.producao;

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

import infotec.sgva.dto.producao.VeiculoDTO;
import infotec.sgva.entities.producao.Veiculo;
import infotec.sgva.enums.VeiculoStatus;
import infotec.sgva.exception.RegraNegocioException;
import infotec.sgva.services.producao.VeiculoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/veiculos")
public class VeiculoController {
	
	@Autowired
	private VeiculoService service;
	
	@GetMapping
	public Page<VeiculoDTO> findAll(Pageable pegeable){
		return service.findAll(pegeable);
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody VeiculoDTO dto){
		try {
			Veiculo entidade = converter(dto);
			service.validarChassi(entidade.getChassi());
			entidade = service.salvar(entidade);
			return new ResponseEntity<>(entidade, HttpStatus.CREATED);
			
			
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	public Veiculo converter(VeiculoDTO dto) {
		Veiculo veiculo = new Veiculo();
		veiculo.setChassi(dto.getChassi());
		veiculo.setMarca(dto.getMarca());
		
		if(veiculo.getStatus() != null) {
			veiculo.setStatus(VeiculoStatus.valueOf(dto.getStatus()));
		}
		return veiculo;
		
	}
	
	

}
