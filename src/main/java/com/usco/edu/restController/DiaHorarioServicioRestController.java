package com.usco.edu.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usco.edu.entities.DiaHorarioServicio;
import com.usco.edu.service.IDiaHorarioServicioService;

@RestController
@RequestMapping(path = "diaHorarioServicio")
public class DiaHorarioServicioRestController {

	@Autowired
	private IDiaHorarioServicioService diaHorarioServicioService;

	@GetMapping(path = "obtener-horarioServicio/{username}")
	public List<DiaHorarioServicio> obtenerHorariosServicio(@PathVariable String username) {
		return diaHorarioServicioService.obtenerDiasHorarioServicio(username);
	}

	@PutMapping(path = "actualizar-horarioServicio/{username}")
	public int actualizarHorarioServicio(@PathVariable String username,
			@RequestBody DiaHorarioServicio horarioServicio) {
		return diaHorarioServicioService.actualizarDiaHorarioServicio(username, horarioServicio);
	}
}
