package infotec.sgva.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import infotec.sgva.dto.FuncaoDTO;
import infotec.sgva.services.FuncaoService;

@RestController
@RequestMapping(value = "/funcoes")
public class FuncaoController {

	@Autowired
	private FuncaoService service;

	@GetMapping
	public Page<FuncaoDTO> findAll(Pageable pegeable) {
		return service.findAll(pegeable);
	}

	@GetMapping(value = "/{id}")
	public FuncaoDTO findById(@PathVariable Long id) {
		return service.findById(id);

	}

}
