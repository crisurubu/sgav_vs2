package infotec.sgva.dto.rh;

import java.text.SimpleDateFormat;

import infotec.sgva.entities.rh.FuncionarioFerias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FuncionarioFeriasDTO {
	
	private Long id;
	private Integer dias;
	private Double valor;	
	private String dataInicio;	
	private String dataTermido;	
	private FuncionarioDTO funcionario;
	
	
	public static FuncionarioFeriasDTO converter(FuncionarioFerias funcionariosFerias) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		FuncionarioFeriasDTO funcionarioFeriasDTO = new FuncionarioFeriasDTO();
		funcionarioFeriasDTO.setId(funcionariosFerias.getId());
		funcionarioFeriasDTO.setDias(funcionariosFerias.getDias());
		funcionarioFeriasDTO.setValor(funcionariosFerias.getValor());
		funcionarioFeriasDTO.setDataInicio(sdf.format(funcionariosFerias.getDataInicio()));
		funcionarioFeriasDTO.setDataTermido(sdf.format(funcionariosFerias.getDataTermido()));
		
		if(funcionariosFerias.getFuncionario() != null) {
			funcionarioFeriasDTO.setFuncionario(FuncionarioDTO.convert(funcionariosFerias.getFuncionario()));
		}
		return funcionarioFeriasDTO;
	}
	
	

}
