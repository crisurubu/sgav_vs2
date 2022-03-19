package infotec.sgva.dto.producao;

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
	
	

}
