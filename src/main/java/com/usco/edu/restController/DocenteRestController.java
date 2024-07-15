package com.usco.edu.restController;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usco.edu.entities.Docente;
import com.usco.edu.service.IDocenteService;

@RestController
@RequestMapping(path = "docente")
public class DocenteRestController {
	
	@Autowired
	IDocenteService docenteService;
	
	@GetMapping(path = "docente-get/{id}/{username}")
	public List<Docente> findByIdentificacion(@PathVariable String id, @PathVariable String username) {
		return docenteService.findByIdentificacion(id, username);
	}

}
