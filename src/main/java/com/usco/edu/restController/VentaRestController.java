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

import com.usco.edu.entities.Venta;
import com.usco.edu.service.IVentaService;

@RestController
@RequestMapping(path = "venta")
public class VentaRestController {

	@Autowired
	private IVentaService ventaService;

	@GetMapping(path = "ejemplo")
	public boolean ejemplo() {

		return true;
	}

	@GetMapping(path = "obtener-venta/{username}/{codigoPersona}/{codigoContrato}")
	public List<Venta> obtenerVentasByPerCodigo(@PathVariable String username, @PathVariable int codigoPersona,
			@PathVariable int codigoContrato) {
		return ventaService.obtenerVentasByPerCodigo(username, codigoPersona, codigoContrato);
	}

	@PostMapping(path = "crear-venta/{username}")
	public int registrarVenta(@PathVariable String username, @RequestBody Venta venta) {
		return ventaService.registrarVenta(username, venta);
	}

	@PutMapping(path = "actualizar-venta/{username}")
	public int actualizarVenta(@PathVariable String username, @RequestBody Venta venta) {
		return ventaService.actualizarVenta(username, venta);
	}
}
