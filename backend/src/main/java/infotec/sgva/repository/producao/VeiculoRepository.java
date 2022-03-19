package infotec.sgva.repository.producao;

import org.springframework.data.jpa.repository.JpaRepository;

import infotec.sgva.entities.producao.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long>{
	
	boolean existsByChassi(String chassi);
	

}
