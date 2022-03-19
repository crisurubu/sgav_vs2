package infotec.sgva.dto.producao;

import java.text.SimpleDateFormat;

import infotec.sgva.entities.producao.VeiculoDateEntrada;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoDateEntradaDTO {
	
	private Long id;
	private String chassi;
	private String email;
	private String dataEntrada;
	
	public static VeiculoDateEntradaDTO convert(VeiculoDateEntrada veiculoDateEntrada) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		VeiculoDateEntradaDTO dto = new VeiculoDateEntradaDTO();
		dto.setId(veiculoDateEntrada.getId().getVeiculo().getId());
		dto.setChassi(veiculoDateEntrada.getId().getVeiculo().getChassi());
		dto.setEmail(veiculoDateEntrada.getId().getFuncionario().getEmail());
		dto.setDataEntrada(sdf.format(veiculoDateEntrada.getDataEntrada()));
		return dto;
	}
	
	

}
