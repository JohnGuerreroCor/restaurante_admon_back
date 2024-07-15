package com.usco.edu.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usco.edu.entities.PoliticaEstamento;
import com.usco.edu.service.IPoliticaService;

@RestController
@RequestMapping(path = "politica")
public class PoliticaRestController {
	
	@Autowired
	private IPoliticaService politicaService;
	
	@GetMapping(path = "obtener-politicaEstamento/{username}")
	public List<PoliticaEstamento> obtenerPoliticaEstamento(@PathVariable String username) {
		
		return politicaService.obtenerPoliticaEstamento(username);
		
	}
	
	@GetMapping(path = "obtener-politicaPorCodigoEstamento/{codigo}/{username}")
	public List<PoliticaEstamento> obtenerPoliticaPorCodigoEstamento(@PathVariable int codigo, @PathVariable String username) {
		
		return politicaService.obtenerPoliticaPorCodigoEstamento(codigo, username);
		
	}
	
	@PutMapping(path = "actualizar-politicaEstamento/{user}")
	public int actualizar(@PathVariable String user, @RequestBody PoliticaEstamento politica) {
		
		return politicaService.actualizar(user, politica);
		
	}

}
