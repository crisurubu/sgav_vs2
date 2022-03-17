package infotec.sgva.dto;

import infotec.sgva.entities.Departamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DepartamentoDTO {
	
	private Long id;	
	private String nome;
	
	
	public static DepartamentoDTO convert(Departamento departamento) {
		DepartamentoDTO departamentoDTO = new DepartamentoDTO();
		departamentoDTO.setId(departamento.getId());
		departamentoDTO.setNome(departamento.getNome());
		return departamentoDTO;
	}

}