package infotec.sgva.controllers.estoque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import infotec.sgva.dto.estoque.FornecedoresDTO;
import infotec.sgva.entities.estoque.Fornecedores;
import infotec.sgva.exception.RegraNegocioException;
import infotec.sgva.services.estoque.FornecedoresService;

@RestController
@RequestMapping(value = "/fornecedores")
public class FornecedoresController {
	
	@Autowired
	private FornecedoresService service;
	
	@PostMapping
	public ResponseEntity<?>salvar(@RequestBody FornecedoresDTO dto){
		try {
			
			Fornecedores entidade = converter(dto);			
			entidade = service.save(entidade);			
			return new ResponseEntity<>(entidade, HttpStatus.CREATED);
			
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}

	private Fornecedores converter(FornecedoresDTO dto) {
		Fornecedores fornecedores = new Fornecedores();
		fornecedores.setId(dto.getId());
		fornecedores.setCnpj(dto.getCnpj());
		fornecedores.setEmail(dto.getEmail());
		fornecedores.setNome(dto.getNome());
		fornecedores.setFone(dto.getFone());
		return fornecedores;
	}
	

}
