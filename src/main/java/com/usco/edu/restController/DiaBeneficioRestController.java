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

import com.usco.edu.entities.DiaBeneficio;
import com.usco.edu.service.IDiaBeneficioService;

@RestController
@RequestMapping(path = "diaBeneficio")
public class DiaBeneficioRestController {

	@Autowired
	private IDiaBeneficioService diaBeneficioService;

	@GetMapping(path = "ejemplo")
	public boolean ejemplo() {

		return true;
	}

	@GetMapping(path = "obtener-diaBeneficio/{username}/{idGrupoGabu}")
	public List<DiaBeneficio> obtenerDiaBeneficio(@PathVariable String username, @PathVariable int idGrupoGabu) {
		return diaBeneficioService.obtenerDiaBeneficio(username, idGrupoGabu);

	}
	
	@GetMapping(path = "obtener-diasBeneficio/{username}")
	public List<DiaBeneficio> obtenerDiasBeneficio(@PathVariable String username) {
		return diaBeneficioService.obtenerDiasBeneficio(username);

	}


	@PostMapping(path = "crear-diaBeneficio/{username}")
	public int crearDiaBeneficio(@PathVariable String username, @RequestBody DiaBeneficio diaBeneficio) {

		return diaBeneficioService.crearDiaBeneficio(username, diaBeneficio);

	}
	
	@PostMapping(path = "crear-diasBeneficio/{username}")
	public int crearDiasBeneficio(@PathVariable String username, @RequestBody List<DiaBeneficio> diasBeneficio) {
	    return diaBeneficioService.crearDiasBeneficio(username, diasBeneficio);
	}


	@PutMapping(path = "actualizar-diaBeneficio/{username}")
	public int actualizarDiaBeneficio(@PathVariable String username, @RequestBody DiaBeneficio diaBeneficio) {

		return diaBeneficioService.actualizarDiaBeneficio(username, diaBeneficio);

	}
}
