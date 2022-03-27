package infotec.sgva.entities.estoque;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_fornecedores", schema = "estoque")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fornecedores {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cnpj;
	private String email;
	private String fone;
	
	@ManyToMany(mappedBy = "fornecedores")
	private Set<Produto> produtos = new HashSet<>();
	

}
