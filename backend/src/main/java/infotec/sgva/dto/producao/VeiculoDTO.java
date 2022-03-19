package infotec.sgva.dto.producao;

import infotec.sgva.entities.producao.Veiculo;
import lombok.Data;

@Data
public class VeiculoDTO {
	
	
	private Long id;
	private String chassi;
	private String marca;	
	private String status;
	
	
	public static VeiculoDTO converter(Veiculo veiculo) {
		VeiculoDTO veiculoDTO = new VeiculoDTO();
		veiculoDTO.setChassi(veiculo.getChassi());
		veiculoDTO.setMarca(veiculo.getMarca());
		veiculoDTO.setStatus(veiculo.getStatus().name());
		
		return veiculoDTO;
		
		
	}
	

}
