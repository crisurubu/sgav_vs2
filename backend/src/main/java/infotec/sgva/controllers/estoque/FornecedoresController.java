package infotec.sgva.controllers.estoque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping
	public Page<FornecedoresDTO> findAll(Pageable pegeable){
		return service.findAll(pegeable);
	}
	
	@GetMapping(value = "/{nome}")
	public ResponseEntity<?> findByNome(@PathVariable("nome") String nome){
		FornecedoresDTO dto = service.findByNome(nome);
		if(dto != null) {
			return ResponseEntity.ok(dto);
		}
		return ResponseEntity.badRequest().body("Fornecedor não encontrado.");
	}
	
	@GetMapping(value = "/buscar/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id){
		FornecedoresDTO dto = service.findById(id);
		if(dto != null) {
			return ResponseEntity.ok(dto);
		}
		
		return ResponseEntity.badRequest().body("Fornecedor não encontrado.");
		
	}
	
		
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
