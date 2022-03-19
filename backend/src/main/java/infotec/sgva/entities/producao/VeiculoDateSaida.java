package infotec.sgva.entities.producao;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import infotec.sgva.entities.rh.Funcionario;

@Entity
@Table(name = "tb_VeiculoDateSaida", schema = "producao")
public class VeiculoDateSaida {
	
	@EmbeddedId
	private VeiculoDateSaidaPK id = new VeiculoDateSaidaPK();
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'z'", timezone = "GMT")
	private Date dataSaida;
	
	
	
	public void setVeiculo(Veiculo veiculo) {
		id.setVeiculo(veiculo);
	}
	public void setFuncionario(Funcionario funcionario) {
		id.setFuncionario(funcionario);
	}
	public VeiculoDateSaidaPK getId() {
		return id;
	}
	public void setId(VeiculoDateSaidaPK id) {
		this.id = id;
	}
	public Date getDataEntrada() {
		return dataSaida;
	}
	public void setDataEntrada(Date dataSaida) {
		this.dataSaida = dataSaida;
	}
	
	
	
	

}

