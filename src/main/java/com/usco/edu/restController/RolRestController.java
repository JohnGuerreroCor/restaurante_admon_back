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

import com.usco.edu.entities.Rol;
import com.usco.edu.service.IRolService;

@RestController
@RequestMapping(path = "roles")
public class RolRestController {
	
	@Autowired
	private IRolService service;

	@PostMapping(path = "rol/{user}")
	public int createRol(@RequestBody Rol r, @PathVariable String user) {

		return service.createRol(user, r);
	}

	@GetMapping(path = "rol-get/{user}")
	public List<Rol> rol(@PathVariable String user) {

		return service.rol(user);
	}
	
	@GetMapping(path = "rol-getByEstamento/{user}/{estamento}")
	public List<Rol> rolByEstamento(@PathVariable String user,  @PathVariable String estamento) {

		return service.rolByEstamento(user, estamento);
	}

	@PutMapping(path = "update-rol/{user}")
	public int updateRol(@PathVariable String user, @RequestBody Rol r) {
		return service.updateRol(user, r);
	}

}
