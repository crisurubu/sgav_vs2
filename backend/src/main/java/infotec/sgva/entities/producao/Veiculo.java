package infotec.sgva.entities.producao;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import infotec.sgva.enums.VeiculoStatus;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_veiculo", schema = "producao")
public class Veiculo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String chassi;
	private String marca;
	
		
	@Enumerated(value = EnumType.STRING)
	private VeiculoStatus status;
	

}
