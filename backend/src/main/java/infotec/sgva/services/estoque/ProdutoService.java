package infotec.sgva.services.estoque;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import infotec.sgva.entities.estoque.Produto;
import infotec.sgva.exception.RegraNegocioException;
import infotec.sgva.repository.estoque.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;
	
	/*@Transactional(readOnly = true)
	public Page<ProdutoDTO> find(PageRequest pageRequest){
		Page<Produto> page = repository.findAll(pageRequest);
		repository.findProdutosPorFornecedores(page.stream().collect(Collectors.toList()));
		return page.map(x -> new ProdutoDTO(x));
	
	}*/
		
	@Transactional(readOnly = true)
	public List<Produto> buscar(Produto produtoFiltro) {
		Example<Produto> example = Example.of(produtoFiltro,
										ExampleMatcher.matching()
										.withIgnoreCase()
										.withStringMatcher(StringMatcher.CONTAINING));
		
		
		return repository.findAll(example);
		
		
	}
	@Transactional(readOnly = true)
	public Optional<Produto> findById(Long id) {
		return repository.findById(id);
		
	}
	
		
	@Transactional
	public Produto salvar(Produto produto) {
		validar(produto);
		return repository.save(produto);
	}

	
	public void validar(Produto produto) {
		if(produto.getCodigo() == null || produto.getCodigo().trim().equals("")) {
			throw new RegraNegocioException("Informe um código válido");
		}
		if(produto.getDescricao() == null || produto.getDescricao().trim().equals("")) {
			throw new RegraNegocioException("Informe uma descrição do produto válida.");
		}
		if(produto.getQnt() == null || produto.getQnt() <= 0) {
			throw new RegraNegocioException("Informe a quantidade do produto.");
		}
		if(produto.getDataEntrada() == null) {
			throw new RegraNegocioException("Informe uma data válida");
		}
				
		
	}
	


	

}
