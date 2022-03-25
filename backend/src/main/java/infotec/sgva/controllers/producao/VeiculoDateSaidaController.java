package infotec.sgva.controllers.producao;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import infotec.sgva.dto.producao.VeiculoDateSaidaDTO;
import infotec.sgva.entities.producao.Veiculo;
import infotec.sgva.entities.producao.VeiculoDateSaida;
import infotec.sgva.entities.rh.Funcionario;
import infotec.sgva.enums.VeiculoStatus;
import infotec.sgva.exception.RegraNegocioException;
import infotec.sgva.services.producao.VeiculoDateSaidaService;
import infotec.sgva.services.producao.VeiculoService;
import infotec.sgva.services.rh.FuncionarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/veiculos/saida")
public class VeiculoDateSaidaController {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	@Autowired
	private VeiculoDateSaidaService service;
	
	@Autowired
	private VeiculoService veiculoService;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	
	@GetMapping
	public Page<VeiculoDateSaidaDTO> findAll(Pageable pegeable){
		return service.findAll(pegeable);
	}
	
		
	@PutMapping
	public ResponseEntity<?> save(@RequestBody VeiculoDateSaidaDTO dto) throws ParseException {
		try {
			VeiculoDateSaida entidade = converter(dto);
			entidade = service.save(entidade);
			return new ResponseEntity<>(entidade, HttpStatus.CREATED);
			
		} 
		catch (RegraNegocioException e) 
		{
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
		
	private VeiculoDateSaida converter(VeiculoDateSaidaDTO dto) throws ParseException {
		VeiculoDateSaida VeiculoDateSaida = new VeiculoDateSaida();
		
		Veiculo veiculo = veiculoService.findByChassi(dto.getChassi())
				.orElseThrow(() -> new RegraNegocioException("Veículo não encontrado.."));
		veiculo.setStatus(VeiculoStatus.FINALIZADO);
		veiculoService.atualizar(veiculo);
		VeiculoDateSaida.setVeiculo(veiculo);
		
		Funcionario funcionario = funcionarioService.obterPorEmail(dto.getEmail())
				.orElseThrow(() -> new RegraNegocioException("Funcionário não encontrado.."));
		VeiculoDateSaida.setFuncionario(funcionario);
		
		VeiculoDateSaida.setDataEntrada(sdf.parse(dto.getDataEntrada()));
		return VeiculoDateSaida;
		
		
	}
	
}
	
	
	
