package infotec.sgva.repository.producao;

import org.springframework.data.jpa.repository.JpaRepository;

import infotec.sgva.entities.producao.VeiculoDateSaida;
import infotec.sgva.entities.producao.VeiculoDateSaidaPK;

public interface VeiculoDateSaidaRepository extends JpaRepository<VeiculoDateSaida, VeiculoDateSaidaPK> {
	
}
