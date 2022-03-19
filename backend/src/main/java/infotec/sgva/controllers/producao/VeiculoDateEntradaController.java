package infotec.sgva.controllers.producao;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import infotec.sgva.dto.producao.VeiculoDateEntradaDTO;
import infotec.sgva.entities.producao.Veiculo;
import infotec.sgva.entities.producao.VeiculoDateEntrada;
import infotec.sgva.entities.rh.Funcionario;
import infotec.sgva.exception.RegraNegocioException;
import infotec.sgva.services.producao.VeiculoDateEntradaService;
import infotec.sgva.services.producao.VeiculoService;
import infotec.sgva.services.rh.FuncionarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/veiculos")
public class VeiculoDateEntradaController {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	@Autowired
	private VeiculoDateEntradaService service;
	
	@Autowired
	private VeiculoService VeiculoService;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@PutMapping("/entrada")
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
		
		Veiculo veiculo = VeiculoService.findByChassi(dto.getChassi())
				.orElseThrow(() -> new RegraNegocioException("Veículo não encontrado.."));
		veiculoDateEntrada.setVeiculo(veiculo);
		
		Funcionario funcionario = funcionarioService.obterPorEmail(dto.getEmail())
				.orElseThrow(() -> new RegraNegocioException("Funcionário não encontrado.."));
		veiculoDateEntrada.setFuncionario(funcionario);
		
		veiculoDateEntrada.setDataEntrada(sdf.parse(dto.getDataEntrada()));
		return veiculoDateEntrada;
		
		
	}
	
}
	
	
	
