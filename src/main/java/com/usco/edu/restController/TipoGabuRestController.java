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

import com.usco.edu.entities.TipoGabu;
import com.usco.edu.service.ITipoGabuService;

@RestController
@RequestMapping(path = "tipoGabu")
public class TipoGabuRestController {

	@Autowired
	private ITipoGabuService tipoGabuService;

	@GetMapping(path = "ejemplo")
	public boolean ejemplo() {

		return true;
	}

	@GetMapping(path = "obtener-tiposGabu/{username}")
	public List<TipoGabu> obtenerTiposGabu(@PathVariable String username) {
		return tipoGabuService.obtenerTiposGabu(username);

	}

	@PostMapping(path = "crear-tipoGabu/{username}")
	public int crearTipoGabu(@PathVariable String username, @RequestBody TipoGabu tipoGabu) {

		return tipoGabuService.crearTipoGabu(username, tipoGabu);

	}

	@PutMapping(path = "actualizar-tipoGabu/{username}")
	public int actualizarTipoGabu(@PathVariable String username, @RequestBody TipoGabu tipoGabu) {

		return tipoGabuService.actualizarTipoGabu(username, tipoGabu);

	}

}
