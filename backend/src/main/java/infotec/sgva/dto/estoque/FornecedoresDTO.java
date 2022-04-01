package infotec.sgva.dto.estoque;

import infotec.sgva.entities.estoque.Fornecedores;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FornecedoresDTO {
	
	private Long id;
	private String nome;
	private String cnpj;
	private String email;
	private String fone;
	
	public FornecedoresDTO(Fornecedores fornecedor) {
		id = fornecedor.getId();
		nome = fornecedor.getNome();
		cnpj = fornecedor.getCnpj();
		email = fornecedor.getEmail();
		fone = fornecedor.getFone();
		
	}
	
	public static FornecedoresDTO convert(Fornecedores fornecedor) {
		FornecedoresDTO dto = new FornecedoresDTO();
		dto.setId(fornecedor.getId());
		dto.setNome(fornecedor.getNome());
		dto.setCnpj(fornecedor.getCnpj());
		dto.setEmail(fornecedor.getEmail());
		dto.setFone(fornecedor.getFone());
		return dto;
		
	}
	
		

}
