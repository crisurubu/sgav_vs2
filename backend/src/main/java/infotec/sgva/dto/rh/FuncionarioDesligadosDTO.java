package infotec.sgva.dto.rh;

import java.text.SimpleDateFormat;

import infotec.sgva.entities.rh.FuncionarioDesligados;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FuncionarioDesligadosDTO {
	
	private Long id;
    private String dataDemissao;	
	private String descricao;	
	private String desligamento;
    private FuncionarioDTO funcionario; 
    
    public static FuncionarioDesligadosDTO convert(FuncionarioDesligados desligamento) {
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    	
    	FuncionarioDesligadosDTO motivoDesligamentoDTO = new FuncionarioDesligadosDTO();
    	motivoDesligamentoDTO.setId(desligamento.getId());
    	motivoDesligamentoDTO.setDataDemissao(sdf.format(desligamento.getDataDemissao()));
    	motivoDesligamentoDTO.setDescricao(desligamento.getDescricao());
    	motivoDesligamentoDTO.setDesligamento(desligamento.getDesligamento().name());
    	    	
    	if(motivoDesligamentoDTO.getFuncionario() != null) {
    		motivoDesligamentoDTO.setFuncionario(FuncionarioDTO.convert(desligamento.getFuncionario()));
		}
    	return motivoDesligamentoDTO;
    }

}
