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

import com.usco.edu.entities.PuestoVigilanciaTipo;
import com.usco.edu.entities.PuestoVigilancia;
import com.usco.edu.service.IPuestoVigilanciaService;

@RestController
@RequestMapping(path = "puestoVigilancia")
public class PuestoVigilanciaRestController {
	
	@Autowired
	IPuestoVigilanciaService puestoVigilanciaServie;
	
	@GetMapping(path = "obtener-puesto-vigilancia-tipo/{username}")
	public List<PuestoVigilanciaTipo> obtenerTipoPuesto(@PathVariable String username) {
		
		return puestoVigilanciaServie.obtenerTipoPuesto(username);
		
	}
	
	@GetMapping(path = "obtener-puesto-vigilancia/{username}")
	public List<PuestoVigilancia> obtenerPuestoVigilancia(@PathVariable String username) {
		
		return puestoVigilanciaServie.obtenerPuestoVigilancia(username);
		
	}
	
	@GetMapping(path = "obtener-puesto-vigilancia-codigo/{codigo}/{username}")
	public List<PuestoVigilancia> obtenerPuestoVigilanciaCodigo(@PathVariable int codigo, @PathVariable String username) {
		
		return puestoVigilanciaServie.obtenerPuestoVigilanciaCodigo(codigo, username);
		
	}
	
	
	@GetMapping(path = "obtener-puesto-vigilancia-bloque-tipo/{subsede}/{tipo}/{username}")
	public List<PuestoVigilancia> obtenerPuestoVigilanciaPorBloqueTipo(@PathVariable int subsede, @PathVariable int tipo, @PathVariable String username) {
		
		return puestoVigilanciaServie.obtenerPuestoVigilanciaPorBloqueTipo(subsede, tipo, username);
		
	}
	
	@PostMapping(path = "registrar-puesto-vigilancia/{user}")
	public int registrar(@PathVariable String user, @RequestBody PuestoVigilancia puestoVigilancia) {

		return puestoVigilanciaServie.registrar(user, puestoVigilancia);
		
	}
	
	@PutMapping(path = "actualizar-puesto-vigilancia/{user}")
	public int actualizar(@PathVariable String user, @RequestBody PuestoVigilancia puestoVigilancia) {
		
		return puestoVigilanciaServie.actualizar(user, puestoVigilancia);
		
	}

}
