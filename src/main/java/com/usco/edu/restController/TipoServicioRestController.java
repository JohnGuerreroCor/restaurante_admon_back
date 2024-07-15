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

import com.usco.edu.entities.TipoServicio;
import com.usco.edu.service.ITipoServicioService;

@RestController
@RequestMapping(path = "tipoServicio")
public class TipoServicioRestController {

	@Autowired
	private ITipoServicioService tipoServicioService;

	@GetMapping(path = "ejemplo")
	public boolean ejemplo() {

		return true;
	}

	@GetMapping(path = "obtener-TipoServicio/{username}")
	public List<TipoServicio> obtenerTiposServicio(@PathVariable String username) {
		return tipoServicioService.obtenerTiposServicio(username);

	}

	@PostMapping(path = "crear-TipoServicio/{username}")
	public int crearTipoServicio(@PathVariable String username, @RequestBody TipoServicio tipoServicio) {

		return tipoServicioService.crearTipoServicio(username, tipoServicio);

	}

	@PutMapping(path = "actualizar-TipoServicio/{username}")
	public int actualizarTipoServicio(@PathVariable String username, @RequestBody TipoServicio tipoServicio) {

		return tipoServicioService.actualizarTipoServicio(username, tipoServicio);

	}

}
