package infotec.sgva.dto.producao;

import infotec.sgva.entities.producao.Apontamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApontamentoDTO {
	
	private Long id;
	private String apontamento;
	private String status;
	private String chassi;
	
	
	public static ApontamentoDTO convert(Apontamento apontamento) {
		ApontamentoDTO dto = new ApontamentoDTO();		
		dto.setId(apontamento.getId());
		dto.setChassi(apontamento.getVeiculo().getChassi());
		dto.setApontamento(apontamento.getApontamento());
		dto.setStatus(apontamento.getStatus().name());
		return dto;
		
	}


}
