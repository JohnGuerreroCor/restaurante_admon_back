package com.usco.edu.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usco.edu.entities.TipoContrato;
import com.usco.edu.service.ITipoContratoService;

@RestController
@RequestMapping(path = "tipoContrato")
public class TipoContratoRestController {

	@Autowired
	private ITipoContratoService tipoContratoService;
	
	
	@GetMapping(path = "ejemplo")
	public boolean ejemplo() {
		
		return true;
	}
	
	@GetMapping(path = "obtener-tiposContrato/{username}")
	public List<TipoContrato> obtenerTiposContrato(@PathVariable String username) {
		return tipoContratoService.obtenerTiposContrato(username);
		
	}
	
	@PostMapping(path = "crear-tipoContrato/{username}")
	public int crearTipoContrato(@PathVariable String username, @RequestBody TipoContrato tipoContrato) {

		return tipoContratoService.crearTipoContrato(username,tipoContrato);
		
	}
	
	@PutMapping(path = "actualizar-tipoContrato/{username}")
	public int actualizarTipoContrato(@PathVariable String username, @RequestBody TipoContrato tipoContrato) {
		
		return tipoContratoService.actualizarTipoContrato(username,tipoContrato);
		
	}
	
	
}
