package infotec.sgva.entities.producao;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import infotec.sgva.entities.rh.Funcionario;
import lombok.Data;

@Data
@Embeddable
public class VeiculoDateSaidaPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@OneToOne
	@JoinColumn(name = "id_veiculo")
	private Veiculo veiculo;
	
	@OneToOne
	@JoinColumn(name = "id_funcionario")
	private Funcionario funcionario;
	
	

}
