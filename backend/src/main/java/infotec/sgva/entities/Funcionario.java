package infotec.sgva.entities;

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

import infotec.sgva.enums.FuncionarioStatus;
import lombok.Data;

@Entity
@Table(name = "tb_funcionario", schema = "rh")
@Data
public class Funcionario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cpf;
	private String email;
	private String celular;
	
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'z'", timezone = "GMT")
	private Date dataAdmissao;
	
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'z'", timezone = "GMT")
	private Date dataDemissao;
	
	@Enumerated(value = EnumType.STRING)
	private FuncionarioStatus status;
	
	@OneToOne
	@JoinColumn(name = "id_funcao")	
	private Funcao funcao;
	
	
	
	

	

}
