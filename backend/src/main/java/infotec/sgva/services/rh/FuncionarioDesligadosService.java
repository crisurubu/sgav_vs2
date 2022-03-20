package infotec.sgva.services.rh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import infotec.sgva.entities.rh.FuncionarioDesligados;
import infotec.sgva.exception.RegraNegocioException;
import infotec.sgva.repository.rh.FuncionarioDesligadosRepository;

@Service
public class FuncionarioDesligadosService {
	
	@Autowired
	private FuncionarioDesligadosRepository repository;
	
	@Transactional
	public FuncionarioDesligados demitir(FuncionarioDesligados desligado) {
		validar(desligado);
		return repository.save(desligado);
		
	}
	
	public void validar(FuncionarioDesligados desligado) {
		
		if(desligado.getDataDemissao() == null ) {
			throw new RegraNegocioException("Informe uma data válida");
		}
		if(desligado.getDescricao() == null || desligado.getDescricao().trim().equals("")) {
			throw new RegraNegocioException("Informe uma descrição válida");
		}
		if(desligado.getFuncionario() == null || desligado.getFuncionario().getId() == null) {
			throw new RegraNegocioException("Informe um funcionário válido");
		}
		
				
		
		
	}
}
