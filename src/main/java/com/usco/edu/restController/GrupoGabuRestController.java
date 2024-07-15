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

import com.usco.edu.entities.GrupoGabu;
import com.usco.edu.entities.GrupoGabuDiasBeneficio;
import com.usco.edu.service.IGrupoGabuService;

@RestController
@RequestMapping(path = "grupoGabu")
public class GrupoGabuRestController {

	@Autowired
	private IGrupoGabuService grupoGabuService;

	@GetMapping(path = "ejemplo")
	public boolean ejemplo() {

		return true;
	}

	@GetMapping(path = "obtener-grupoGabu/{username}/{codigo}")
	public List<GrupoGabu> obtenerGrupoGabu(@PathVariable String username, @PathVariable int codigo) {
		return grupoGabuService.obtenerGrupoGabu(username, codigo);

	}

	@GetMapping(path = "obtener-grupoGabus/{username}")
	public List<GrupoGabu> obtenerGrupoGabus(@PathVariable String username) {
		return grupoGabuService.obtenerGrupoGabus(username);

	}

	@PostMapping(path = "crear-grupoGabu/{username}")
	public int crearGrupoGabu(@PathVariable String username, @RequestBody GrupoGabu grupoGabu) {

		return grupoGabuService.crearGrupoGabu(username, grupoGabu);

	}

	@PutMapping(path = "actualizar-grupoGabu/{username}")
	public int actualizarGrupoGabu(@PathVariable String username, @RequestBody GrupoGabu grupoGabu) {

		return grupoGabuService.actualizarGrupoGabu(username, grupoGabu);

	}
}
