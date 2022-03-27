package infotec.sgva.repository.estoque;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import infotec.sgva.entities.estoque.Fornecedores;

public interface FornecedoresRepository extends JpaRepository<Fornecedores, Long> {
	
	Optional<Fornecedores> findByCnpj(String cnpj);
	boolean existsByCnpj(String cnpj);

}
