package infotec.sgva.dto;

import infotec.sgva.entities.Funcao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class FuncaoDTO {
	
	private Long id;
	private String nome;
	private Double salario;
	private DepartamentoDTO departamento;
	
	public static FuncaoDTO convert(Funcao funcao) {
		FuncaoDTO funcaoDTO = new FuncaoDTO();
		funcaoDTO.setId(funcao.getId());
		funcaoDTO.setNome(funcao.getNome());
		funcaoDTO.setSalario(funcao.getSalario());
		
		if(funcao.getDepartamento() != null) {
			funcaoDTO.setDepartamento(DepartamentoDTO.convert(funcao.getDepartamento()));
		}
		return funcaoDTO;
	}
	
}