package infotec.sgva.services.producao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import infotec.sgva.entities.producao.VeiculoDateEntrada;
import infotec.sgva.repository.producao.VeiculoDateEntradaRepository;

@Service
public class VeiculoDateEntradaService {
	
	
	
	@Autowired
	private VeiculoDateEntradaRepository repository;
	
	@Transactional
	public VeiculoDateEntrada save(VeiculoDateEntrada veiculoDateEntrada)  {
		return repository.save(veiculoDateEntrada);
		
		
		
	}
	

}
