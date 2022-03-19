package infotec.sgva.dto.producao;

import java.text.SimpleDateFormat;

import infotec.sgva.entities.producao.VeiculoDateSaida;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoDateSaidaDTO {
	
	private Long id;
	private String chassi;
	private String email;
	private String dataEntrada;
	
	public static VeiculoDateSaidaDTO convert(VeiculoDateSaida VeiculoDateSaida) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		VeiculoDateSaidaDTO dto = new VeiculoDateSaidaDTO();
		dto.setId(VeiculoDateSaida.getId().getVeiculo().getId());
		dto.setChassi(VeiculoDateSaida.getId().getVeiculo().getChassi());
		dto.setEmail(VeiculoDateSaida.getId().getFuncionario().getEmail());
		dto.setDataEntrada(sdf.format(VeiculoDateSaida.getDataEntrada()));
		return dto;
	}
	
	

}
