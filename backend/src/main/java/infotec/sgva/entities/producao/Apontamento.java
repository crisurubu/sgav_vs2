package infotec.sgva.entities.producao;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import infotec.sgva.enums.ApontamentosStatus;

@Entity
@Table(name = "tb_apontmento", schema = "producao")
public class Apontamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String Apontamento;
	
	@OneToOne
	@JoinColumn(name = "id_veiculo")
	private Veiculo veiculo;
	
	@Enumerated(value = EnumType.STRING)
	private ApontamentosStatus status;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApontamento() {
		return Apontamento;
	}

	public void setApontamento(String apontamento) {
		Apontamento = apontamento;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public ApontamentosStatus getStatus() {
		return status;
	}

	public void setStatus(ApontamentosStatus status) {
		this.status = status;
	}
	
	

}
