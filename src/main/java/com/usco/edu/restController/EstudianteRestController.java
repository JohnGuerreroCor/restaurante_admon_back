package com.usco.edu.restController;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usco.edu.entities.Estudiante;
import com.usco.edu.service.IEstudianteService;

@RestController
@RequestMapping(path = "estudiante")
public class EstudianteRestController {
	
	@Autowired
	IEstudianteService estudianteservice;
	
	@GetMapping(path = "estudiante-get/{codigo}/{username}")
	public List<Estudiante> findByCodigo(@PathVariable String codigo, @PathVariable String username) {
		return estudianteservice.findByCodigo(codigo, username);
	}
	
	@GetMapping(path = "buscar-estudiante-identificacion/{id}")
	public List<Estudiante> buscarIdentificacion(@PathVariable String id) {
		return estudianteservice.buscarIdentificacion(id);
	}
	
	@GetMapping(path = "obtener-estudiantes/{username}")
	public List<Estudiante> obtenerEstudiantes(@PathVariable String username) {
		return estudianteservice.obtenerEstudiantes(username);
	}
	
}
