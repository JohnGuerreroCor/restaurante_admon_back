package com.usco.edu.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usco.edu.entities.PersonaCarnet;
import com.usco.edu.service.IPersonaService;

@RestController
@RequestMapping(path = "persona")
public class PersonaRestController {
	
	@Autowired
	IPersonaService personaService;
	
	@GetMapping("/obtener-persona-codigo/{codigo}/{userdb}")
	public List<PersonaCarnet> buscarPorPerCodigo(@PathVariable("codigo") int codigo, @PathVariable("userdb") String userdb) {
		return personaService.buscarPorPerCodigo(codigo, userdb);
	}
	
	@GetMapping("/obtener-persona-identificacion/{id}/{userdb}")
	public List<PersonaCarnet> buscarPorIdentificacion(@PathVariable("id") String id, @PathVariable("userdb") String userdb) {
		return personaService.buscarPorIdentificacion(id, userdb);
	}

}
