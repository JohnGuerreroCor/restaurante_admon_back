package com.usco.edu.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usco.edu.entities.Administrativo;
import com.usco.edu.service.IAdministrativoCarnetService;

@RestController
@RequestMapping(path = "administrativo")
public class AdministrativoCarnetRestController {
	
	@Autowired
	IAdministrativoCarnetService administrativoCarnetService;
	
	@GetMapping(path = "administrativo-get/{id}/{username}")
	public List<Administrativo> findByIdentificacion(@PathVariable String id, @PathVariable String username) {
		
		return administrativoCarnetService.findByIdentificacion(id, username);
		
	}

}
