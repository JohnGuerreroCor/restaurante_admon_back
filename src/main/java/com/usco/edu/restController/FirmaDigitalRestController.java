package com.usco.edu.restController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.usco.edu.entities.Rector;
import com.usco.edu.entities.Firma;
import com.usco.edu.entities.Respuesta;
import com.usco.edu.service.IFirmaDigitalService;

@RestController
@RequestMapping(path = "firmaDigital")
public class FirmaDigitalRestController {
	
	@Autowired
	IFirmaDigitalService firmaService;
	
	@GetMapping(path = "obtener-rectorActual/{username}")
	public List<Rector> buscarRectorActual(@PathVariable String username) {
		
		return firmaService.buscarRectorActual(username);
		
	}
	
	@GetMapping(path = "obtener-firma-activa/{username}")
	public List<Firma> buscarFirmaActiva(@PathVariable String username) {
		
		return firmaService.buscarFirmaActiva(username);
		
	}
	
	@GetMapping(path = "obtener-firma/{username}")
	public List<Firma> buscarFirma(@PathVariable String username) {
		
		return firmaService.buscarFirma(username);
		
	}
	
	@PostMapping("registrar-firma/{user}/{perCodigo}/{uaa}")
	public void registrarFirma(@PathVariable String user, @PathVariable Long perCodigo, @RequestPart MultipartFile archivo, HttpServletRequest request, @PathVariable int uaa, @RequestParam String json) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		Firma firma;
		try {
			firma = objectMapper.readValue(json, Firma.class);
			firma.setRuta(firmaService.subirArchivo(archivo, perCodigo, uaa, user, request));
			System.out.println("Entro");
			firmaService.registrarFirma(firma, user);

		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@PutMapping("actualizar-firma/{user}")
	public void update(@RequestBody Firma firma, @PathVariable String user) {
		
		firmaService.actualizarFirmaEstado(firma, user);
	}
	
	@GetMapping("mirar-archivo/{codigo}/{user}")
	public ResponseEntity<InputStreamResource> mirarArchivo(@PathVariable Long codigo, @PathVariable String user, HttpServletResponse response) throws Exception{
		ByteArrayInputStream stream = firmaService.mirarArchivo(codigo, user, response);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=\" firma.png\"");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
		
	}
	
	@GetMapping("enviar-email-rector/{email}/{firma}/{nombrePersona}/{fecha}/{nombre}/{correo}/{cargo}")
	public Respuesta enviarEmail(@PathVariable String email, @PathVariable String firma, @PathVariable String nombrePersona, @PathVariable String fecha, @PathVariable String nombre, @PathVariable String correo, @PathVariable String cargo) {
		
		return firmaService.enviarEmail(email, firma, nombrePersona, fecha, nombre, correo, cargo);
		
	}
	
}
