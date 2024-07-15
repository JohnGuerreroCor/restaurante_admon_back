package com.usco.edu.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usco.edu.entities.Estamento;
import com.usco.edu.service.IEstamentoService;

@RestController
@RequestMapping(path = "estamentos")
public class EstamentoRestController {
	
	@Autowired
	private IEstamentoService service;

	@GetMapping("find/{user}")
	public List<Estamento> estamentos(@PathVariable String user) {
		return service.estamentos(user);
	}

}
