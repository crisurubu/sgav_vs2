package infotec.sgva.entities.producao;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import infotec.sgva.entities.rh.Funcionario;

@Entity
@Table(name = "tb_veiculoDateEntrada", schema = "producao")
public class VeiculoDateEntrada {
	
	@EmbeddedId
	private VeiculoDateEntradaPK id = new VeiculoDateEntradaPK();
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'z'", timezone = "GMT")
	private Date dataEntrada;
	
	
	
	public void setVeiculo(Veiculo veiculo) {
		id.setVeiculo(veiculo);
	}
	public void setFuncionario(Funcionario funcionario) {
		id.setFuncionario(funcionario);
	}
	public VeiculoDateEntradaPK getId() {
		return id;
	}
	public void setId(VeiculoDateEntradaPK id) {
		this.id = id;
	}
	public Date getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	
	
	
	

}
