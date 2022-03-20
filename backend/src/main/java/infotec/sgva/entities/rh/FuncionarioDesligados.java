package infotec.sgva.entities.rh;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import infotec.sgva.enums.MotivoDesligamento;
import lombok.Data;

@Entity
@Table(name = "tb_funcionariosDesligados", schema = "rh")
@Data
public class FuncionarioDesligados {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'z'", timezone = "GMT")
	private Date dataDemissao;
	
	private String descricao;
	
	@Enumerated(value = EnumType.STRING)
	private MotivoDesligamento desligamento;
	
	@OneToOne
	@JoinColumn(name = "id_funcionario")
	private Funcionario funcionario;
	
	

}
