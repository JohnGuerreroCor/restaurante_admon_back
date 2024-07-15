package com.usco.edu.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usco.edu.entities.PiboteAdicion;
import com.usco.edu.service.IPiboteAdicionService;

@RestController
@RequestMapping(path = "piboteAdicion")
public class PiboteAdicionRestController {

	@Autowired
	private IPiboteAdicionService piboteAdicionService;

	@PostMapping(path = "crear-piboteAdicion/{username}")
	public int crearPiboteAdicion(@PathVariable String username, @RequestBody PiboteAdicion piboteAdicion) {
		return piboteAdicionService.crearPibote(username, piboteAdicion);
	}

	@PutMapping(path = "actualizar-piboteAdicion/{username}")
	public int actualizarPiboteAdicion(@PathVariable String username, @RequestBody PiboteAdicion piboteAdicion) {
		return piboteAdicionService.actualizarPibote(username, piboteAdicion);
	}

}
