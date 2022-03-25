package infotec.sgva.services.rh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import infotec.sgva.entities.rh.FuncionarioFerias;
import infotec.sgva.exception.RegraNegocioException;
import infotec.sgva.repository.rh.FuncionarioFeriasRepository;

@Service
public class FuncionarioFeriasService {
	
	@Autowired
	private FuncionarioFeriasRepository repository;
	
	
	@Transactional
	public FuncionarioFerias ferias(FuncionarioFerias funcionarioFerias) {
		validar(funcionarioFerias);					
		return repository.save(funcionarioFerias);		
		
	}
	
	public void validar(FuncionarioFerias funcionarioFerias) {
		if(funcionarioFerias.getDias() == null || funcionarioFerias.getDias() < 0) {
			throw new RegraNegocioException("Informe um valor de dias válido");
		}
		if(funcionarioFerias.getValor() == null || funcionarioFerias.getValor() < 0) {
			throw new RegraNegocioException("Valor não é válido");
		}
		if(funcionarioFerias.getDataInicio() == null) {
			throw new RegraNegocioException("Informe uma data Início válido");
		}
		if(funcionarioFerias.getDataTermido() == null) {
			throw new RegraNegocioException("Informe uma data Térmido válido");
		}
		if(funcionarioFerias.getFuncionario() == null || funcionarioFerias.getFuncionario().getId() == null) {
			throw new RegraNegocioException("Informe um funcionário");
		}
				
		
		
	}
	
		
	
}
