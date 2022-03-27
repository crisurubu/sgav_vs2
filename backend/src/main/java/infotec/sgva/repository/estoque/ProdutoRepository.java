package infotec.sgva.repository.estoque;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import infotec.sgva.entities.estoque.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	@Query("SELECT obj FROM Produto obj JOIN FETCH obj.fornecedores WHERE obj IN :produtos")
	List<Produto> findProdutosPorFornecedores(List<Produto> produtos);

}
