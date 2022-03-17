package infotec.sgva.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import infotec.sgva.dto.DepartamentoDTO;
import infotec.sgva.services.DepartamentoService;

@RestController
@RequestMapping(value = "/departamentos")
public class DepartamentoController {
	
	@Autowired
	private DepartamentoService service;
	
	@GetMapping
	public Page<DepartamentoDTO> findAll(Pageable pegeable){
		return service.findAll(pegeable);
	}
	
	@GetMapping(value = "/{id}")
	public DepartamentoDTO findById(@PathVariable Long id){
		return service.findById(id);
		
		
	}


}
