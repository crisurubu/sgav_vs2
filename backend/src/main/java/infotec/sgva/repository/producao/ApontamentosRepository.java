package infotec.sgva.repository.producao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import infotec.sgva.entities.producao.Apontamentos;
import infotec.sgva.entities.producao.ApontamentosPK;

public interface ApontamentosRepository extends JpaRepository<Apontamentos, ApontamentosPK> {
	
	@Query(value = "SELECT a FROM Apontamentos a where a.id = :idVeiculo")
	Optional<Apontamentos> buscarApontamentoPorIdVeiculo(@Param("idVeiculo") ApontamentosPK id);
	
	

}
