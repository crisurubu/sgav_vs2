package infotec.sgva.repository.estoque;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import infotec.sgva.entities.estoque.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	/*
	@Query("SELECT obj FROM Produto obj JOIN FETCH obj.fornecedores WHERE obj IN :produtos")
	List<Produto> findProdutosPorFornecedores(List<Produto> produtos);*/
	
	@Query("SELECT p FROM Produto p JOIN p.fornecedor f WHERE f.id = :id")
	List<Produto> findPrdutosPorIdFornecedores(List<Produto>  id);
	
	Optional<Produto> findById(Long id);
	

}
