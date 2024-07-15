package com.usco.edu.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usco.edu.entities.TipoDocumento;
import com.usco.edu.service.ITipoDocumentoService;

@RestController
@RequestMapping(path = "tipoDocumento")
public class TipoDocumentoRestController {
	
	@Autowired
	ITipoDocumentoService tipoDocumentoService;
	
	@GetMapping(path = "obtener-tipo-documento/{username}")
	public List<TipoDocumento> obtenerTiposDocumentos(@PathVariable String username) {
		
		return tipoDocumentoService.obtenerTiposDocumentos(username);
		
	}

}
