package com.usco.edu.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usco.edu.entities.Tercero;
import com.usco.edu.service.ITerceroService;

@RestController
@RequestMapping(path = "tercero")
public class TerceroRestController {
	
	@Autowired
	private ITerceroService terceroService;
	
	@GetMapping(path = "obtener-tercero/{id}/{username}")
	public List<Tercero> obtenerTerceroId(@PathVariable String id, @PathVariable String username) {
		
		return terceroService.obtenerTerceroId(id, username);
		
	}
	
	@PostMapping(path = "registrar-tercero/{user}")
	public int registrar(@PathVariable String user, @RequestBody Tercero tercero) {

		return terceroService.registrar(user, tercero);
		
	}
	
	@PutMapping(path = "actualizar-email-tercero/{user}")
	public int actualizar(@PathVariable String user, @RequestBody Tercero tercero) {
		
		return terceroService.actualizar(user, tercero);
		
	}

}
