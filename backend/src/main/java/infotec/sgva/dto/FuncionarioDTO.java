package infotec.sgva.dto;

import java.text.SimpleDateFormat;

import infotec.sgva.entities.Funcionario;
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
	private String dataDemissao;
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
		funcionarioDTO.setDataDemissao(sdf.format(funcionario.getDataDemissao()));
		
		if(funcionario.getFuncao() != null) {
			funcionarioDTO.setFuncao(FuncaoDTO.convert(funcionario.getFuncao()));
		}
		
		funcionarioDTO.setStatus(funcionario.getStatus().name());
		
		return funcionarioDTO;
	}
	
	
	
	

}
