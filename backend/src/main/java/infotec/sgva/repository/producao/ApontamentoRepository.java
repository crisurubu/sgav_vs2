package infotec.sgva.repository.producao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import infotec.sgva.entities.producao.Apontamento;

public interface ApontamentoRepository extends JpaRepository<Apontamento, Long>{
	
	@Query(value = "SELECT a FROM Apontamento a where a.veiculo.id = :idVeiculo")
	List<Apontamento> buscarApontamentoPorIdVeiculo(@Param("idVeiculo") Long id);

}
