package infotec.sgva.dto.producao;

import infotec.sgva.entities.producao.VeiculoApontamentos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoApontamentosDTO {
	
	private Long id;
	private Long apontamentosId;
	
	
	public static VeiculoApontamentosDTO converter(VeiculoApontamentos veiculoApontamentos) {
		VeiculoApontamentosDTO dto = new VeiculoApontamentosDTO();
		dto.setId(veiculoApontamentos.getId());
		return dto;
	}
	
	
	public Long getapontamentosId() {
		return apontamentosId;
	}

	public void setApontamentosId(Long apontamentosId) {
		this.apontamentosId = apontamentosId;
	}
	
	

}
