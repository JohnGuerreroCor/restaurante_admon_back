package com.usco.edu.restController;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usco.edu.entities.Graduado;
import com.usco.edu.service.IGraduadoService;

@RestController
@RequestMapping(path = "graduado")
public class GraduadoRestController {
	
	@Autowired
	IGraduadoService graduadoService;
	
	@GetMapping(path = "obtener-graduado/{codigo}/{username}")
	public List<Graduado> buscarPorCodigo(@PathVariable String codigo, @PathVariable String username) {
		return graduadoService.buscarPorCodigo(codigo, username);
	}
	
}
