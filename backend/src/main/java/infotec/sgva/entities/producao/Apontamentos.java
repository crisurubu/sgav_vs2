package infotec.sgva.entities.producao;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import infotec.sgva.enums.ApontamentosStatus;

@Entity
@Table(name = "tb_apontmentos", schema = "producao")
public class Apontamentos {
	
	
	
	@EmbeddedId
	private ApontamentosPK id = new ApontamentosPK();
	private String Apontamento;
	
	@Enumerated(value = EnumType.STRING)
	private ApontamentosStatus status;
	
	

	public void setVeiculo(Veiculo veiculo) {
		id.setVeiculo(veiculo);
	}

	public ApontamentosPK getId() {
		return id;
	}

	public void setId(ApontamentosPK id) {
		this.id = id;
	}

	public String getApontamento() {
		return Apontamento;
	}

	public void setApontamento(String apontamento) {
		Apontamento = apontamento;
	}

	public ApontamentosStatus getStatus() {
		return status;
	}

	public void setStatus(ApontamentosStatus status) {
		this.status = status;
	}
	
	
	

}
