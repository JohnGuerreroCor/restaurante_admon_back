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

import com.usco.edu.entities.Contrato;
import com.usco.edu.service.IContratoService;

@RestController
@RequestMapping(path = "contrato")
public class ContratoRestController {

	@Autowired
	private IContratoService contratoService;

	@GetMapping(path = "ejemplo")
	public boolean ejemplo() {

		return true;
	}

	@GetMapping(path = "obtener-contrato/{username}/{codigo}")
	public List<Contrato> obtenerContrato(@PathVariable String username, @PathVariable int codigo) {
		return contratoService.obtenerContrato(username, codigo);
	}

	@GetMapping(path = "obtener-contrato/{username}")
	public List<Contrato> obtenerContrato(@PathVariable String username) {
		return contratoService.obtenerContratos(username);
	}

	@PostMapping(path = "crear-contrato/{username}")
	public int crearContrato(@PathVariable String username, @RequestBody Contrato contrato) {
		return contratoService.crearContrato(username, contrato);
	}

	@PutMapping(path = "actualizar-contrato/{username}")
	public int actualizarContrato(@PathVariable String username, @RequestBody Contrato contrato) {
		return contratoService.actualizarContrato(username, contrato);
	}
}
