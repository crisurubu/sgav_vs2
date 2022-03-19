package infotec.sgva.repository.producao;

import org.springframework.data.jpa.repository.JpaRepository;

import infotec.sgva.entities.producao.VeiculoDateEntrada;
import infotec.sgva.entities.producao.VeiculoDateEntradaPK;

public interface VeiculoDateEntradaRepository extends JpaRepository<VeiculoDateEntrada, VeiculoDateEntradaPK> {

}
