package infotec.sgva.services.estoque;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import infotec.sgva.dto.estoque.ProdutoDTO;
import infotec.sgva.entities.estoque.Produto;
import infotec.sgva.repository.estoque.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;
	
	@Transactional(readOnly = true)
	public Page<ProdutoDTO> find(PageRequest pageRequest){
		Page<Produto> page = repository.findAll(pageRequest);
		repository.findProdutosPorFornecedores(page.stream().collect(Collectors.toList()));
		return page.map(x -> new ProdutoDTO(x));
	
	}
	

}
