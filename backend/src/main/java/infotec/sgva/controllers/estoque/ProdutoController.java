package infotec.sgva.controllers.estoque;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import infotec.sgva.dto.estoque.ProdutoDTO;
import infotec.sgva.entities.estoque.Fornecedores;
import infotec.sgva.entities.estoque.Produto;
import infotec.sgva.enums.CategoriaProduto;
import infotec.sgva.enums.ProdutoStatus;
import infotec.sgva.exception.RegraNegocioException;
import infotec.sgva.services.estoque.FornecedoresService;
import infotec.sgva.services.estoque.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Autowired
	private ProdutoService service;
	
	@Autowired
	private FornecedoresService fornecedoresService;
	
		
	
	/*@GetMapping
	public ResponseEntity<Page<ProdutoDTO>> findAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size
			) {
		
		PageRequest pageRequest = PageRequest.of(page, size);
		Page<ProdutoDTO> list = service.find(pageRequest);
		return ResponseEntity.ok(list);
	}*/
	
	
	@GetMapping
	public ResponseEntity<?> buscar(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "id") Long id
								 ) {
		
		
		Optional<Produto> produto = service.findById(id);
		if(!produto.isPresent()) {
			return ResponseEntity.badRequest().body("Não foi possível realizar a consulta. Produto não encontrado para id informado.");
			
		}
		Produto buscado = new Produto();
		List<Produto> produtos = service.buscar(buscado);
		return ResponseEntity.ok(produtos);
				
	}
	
	
	
	@PostMapping
	public ResponseEntity<?>salvar(@RequestBody ProdutoDTO dto) throws ParseException{
		try {
			
			Produto produto = converter(dto);			
			produto = service.salvar(produto);			
			return new ResponseEntity<>(produto, HttpStatus.CREATED);	
			
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	private Produto converter(ProdutoDTO dto) throws ParseException {
		Produto produto = new Produto();
		produto.setId(dto.getId());
		produto.setDescricao(dto.getDescricao());
		produto.setCodigo(dto.getCodigo());
		produto.setQnt(dto.getQnt());
		produto.setDataEntrada(sdf.parse(dto.getDataEntrada()));
		
		if(dto.getCategoria() != null) {
			produto.setCategoria(CategoriaProduto.valueOf(dto.getCategoria()));
		}
		if(dto.getStatus() != null) {
			produto.setStatus(ProdutoStatus.valueOf(dto.getStatus()));
		}
		
		Fornecedores fornecedor = fornecedoresService.buscaPorId(dto.getFornecedor().getId())
														  .orElseThrow(() -> new RegraNegocioException("Fornecedor não encontrada para Id Informado"));;
		
		produto.setFornecedor(fornecedor);
			
		return produto;		
	}
	
	
	
	
	

}
