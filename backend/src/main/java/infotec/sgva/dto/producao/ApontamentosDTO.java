package infotec.sgva.dto.producao;

import infotec.sgva.entities.producao.Apontamentos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApontamentosDTO {
	
	
	private Long id;
	private String apontamento;
	private String status;
	private String chassi;
	
	
	public static ApontamentosDTO convert(Apontamentos apontamento) {
		ApontamentosDTO dto = new ApontamentosDTO();		
		dto.setId(apontamento.getId().getVeiculo().getId());
		dto.setChassi(apontamento.getId().getVeiculo().getChassi());
		dto.setApontamento(apontamento.getApontamento());
		dto.setStatus(apontamento.getStatus().name());
		return dto;
		
	}

}
