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

import infotec.sgva.dto.producao.VeiculoDateEntradaDTO;
import infotec.sgva.entities.producao.Veiculo;
import infotec.sgva.entities.producao.VeiculoDateEntrada;
import infotec.sgva.entities.rh.Funcionario;
import infotec.sgva.enums.VeiculoStatus;
import infotec.sgva.exception.RegraNegocioException;
import infotec.sgva.services.producao.VeiculoDateEntradaService;
import infotec.sgva.services.producao.VeiculoService;
import infotec.sgva.services.rh.FuncionarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/veiculos/entrada")
public class VeiculoDateEntradaController {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	@Autowired
	private VeiculoDateEntradaService service;
	
	@Autowired
	private VeiculoService veiculoService;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	
	@GetMapping
	public Page<VeiculoDateEntradaDTO> findAll(Pageable pegeable){
		return service.findAll(pegeable);
	}
	
		
	@PutMapping
	public ResponseEntity<?> save(@RequestBody VeiculoDateEntradaDTO dto) throws ParseException {
		try {
			VeiculoDateEntrada entidade = converter(dto);
			entidade = service.save(entidade);
			return new ResponseEntity<>(entidade, HttpStatus.CREATED);
			
		} 
		catch (RegraNegocioException e) 
		{
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	
	private VeiculoDateEntrada converter(VeiculoDateEntradaDTO dto) throws ParseException {
		VeiculoDateEntrada veiculoDateEntrada = new VeiculoDateEntrada();
		
		Veiculo veiculo = veiculoService.findByChassi(dto.getChassi())
				.orElseThrow(() -> new RegraNegocioException("Veículo não encontrado.."));
		veiculo.setStatus(VeiculoStatus.EMPRODUÇÃO);
		veiculoService.atualizar(veiculo);		
		veiculoDateEntrada.setVeiculo(veiculo);
		
		
		Funcionario funcionario = funcionarioService.obterPorEmail(dto.getEmail())
				.orElseThrow(() -> new RegraNegocioException("Funcionário não encontrado.."));
		veiculoDateEntrada.setFuncionario(funcionario);
		
		veiculoDateEntrada.setDataEntrada(sdf.parse(dto.getDataEntrada()));
		return veiculoDateEntrada;
		
		
	}
	
}
	
	
	
