package infotec.sgva.entities.producao;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_veiculoApontamentos", schema = "producao")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VeiculoApontamentos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@OneToMany(mappedBy = "Apontamento")
	private Set<Apontamentos> apontamentos = new HashSet<>();

}
