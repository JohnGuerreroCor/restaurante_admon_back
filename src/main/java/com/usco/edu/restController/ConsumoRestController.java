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

import com.usco.edu.entities.Consumo;
import com.usco.edu.service.IConsumoService;

@RestController
@RequestMapping(path = "consumo")
public class ConsumoRestController {

	@Autowired
	private IConsumoService consumoService;

	@GetMapping(path = "ejemplo")
	public boolean ejemplo() {

		return true;
	}

	@GetMapping(path = "obtener-consumo/{username}/{codigo}")
	public List<Consumo> obtenerConsumo(@PathVariable String username, @PathVariable int codigo) {
		return consumoService.obtenerConsumo(username, codigo);
	}

	@GetMapping(path = "obtener-consumo/{username}")
	public List<Consumo> obtenerConsumo(@PathVariable String username) {
		return consumoService.obtenerConsumos(username);
	}

	@PostMapping(path = "crear-consumo/{username}")
	public int crearConsumo(@PathVariable String username, @RequestBody Consumo consumo) {
		return consumoService.crearConsumo(username, consumo);
	}

	@PutMapping(path = "actualizar-consumo/{username}")
	public int actualizarConsumo(@PathVariable String username, @RequestBody Consumo consumo) {
		return consumoService.actualizarConsumo(username, consumo);
	}
}
