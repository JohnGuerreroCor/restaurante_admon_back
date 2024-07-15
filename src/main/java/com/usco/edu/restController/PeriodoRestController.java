package com.usco.edu.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usco.edu.entities.Periodo;
import com.usco.edu.service.IPeriodoService;

@RestController
@RequestMapping(path = "periodo")
public class PeriodoRestController {

	@Autowired
	private IPeriodoService periodoService;

	@GetMapping(path = "ejemplo")
	public boolean ejemplo() {

		return true;
	}

	@GetMapping(path = "obtener-periodos/{username}")
	public List<Periodo> obtenerPeriodos(@PathVariable String username) {
		return periodoService.obtenerPeriodos(username);

	}

	@GetMapping(path = "obtener-periodo/{username}/{codigo}")
	public List<Periodo> obtenerPeriodo(@PathVariable String username, @PathVariable int codigo) {
		return periodoService.obtenerPeriodo(username, codigo);
	}
}
