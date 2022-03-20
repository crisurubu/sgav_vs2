package infotec.sgva.dto.rh;

import java.text.SimpleDateFormat;

import infotec.sgva.entities.rh.Funcionario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FuncionarioDTO {
	
	
	private Long id;
	private String nome;
	private String cpf;
	private String email;
	private String celular;
	private String dataAdmissao;
	private FuncaoDTO funcao;
	private String status;
	
	public static FuncionarioDTO convert(Funcionario funcionario) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setId(funcionario.getId());
		funcionarioDTO.setNome(funcionario.getNome());
		funcionarioDTO.setCpf(funcionario.getCpf());
		funcionarioDTO.setEmail(funcionario.getEmail());
		funcionarioDTO.setCelular(funcionario.getCelular());
		funcionarioDTO.setDataAdmissao(sdf.format(funcionario.getDataAdmissao()));
				
		if(funcionario.getFuncao() != null) {
			funcionarioDTO.setFuncao(FuncaoDTO.convert(funcionario.getFuncao()));
		}
		
		funcionarioDTO.setStatus(funcionario.getStatus().name());
		
		return funcionarioDTO;
	}
	
	
	
	

}
