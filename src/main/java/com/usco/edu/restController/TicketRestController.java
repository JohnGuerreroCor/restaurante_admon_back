package com.usco.edu.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usco.edu.entities.Respuesta;
import com.usco.edu.entities.Ticket;
import com.usco.edu.service.ITicketService;

@RestController
@RequestMapping(path = "ticket")
public class TicketRestController {
	
	@Autowired
	private ITicketService ticketService;
	
	@GetMapping(path = "obtener-tickets/{tipoTicket}/{username}")
	public List<Ticket> obtenerTickets(@PathVariable int tipoTicket, @PathVariable String username) {
		
		return ticketService.obtenerTickets(tipoTicket, username);
		
	}
	
	@GetMapping(path = "obtener-ticket-tercodigo/{codigo}/{tipoTicket}/{username}")
	public List<Ticket> obtenerTicketPorTerCodigo(@PathVariable int codigo, @PathVariable int tipoTicket, @PathVariable String username) {
		
		return ticketService.obtenerTicketPorTerCodigo(codigo, tipoTicket, username);
		
	}
	
	@GetMapping(path = "obtener-ticket-percodigo/{codigo}/{tipoTicket}/{username}")
	public List<Ticket> obtenerTicketPorPerCodigo(@PathVariable int codigo, @PathVariable int tipoTicket, @PathVariable String username) {
		
		return ticketService.obtenerTicketPorPerCodigo(codigo, tipoTicket, username);
		
	}
	
	@GetMapping(path = "obtener-ticket-identificacion/{identificacion}/{username}")
	public List<Ticket> obtenerTicketPorIdentificacion(@PathVariable String identificacion, @PathVariable String username) {
		
		return ticketService.obtenerTicketPorIdentificacion(identificacion, username);
		
	}
	
	@PostMapping(path = "registrar-ticket/{user}")
	public int registrar(@PathVariable String user, @RequestBody Ticket ticket) {

		return ticketService.registrar(user, ticket);
		
	}
	
	@GetMapping("enviar-ticket-visitante-email/{email}/{nombre}/{id}/{lugar}/{registro}/{vigencia}/{qr}")
	public Respuesta enviarTicketVisitanteEmail(@PathVariable String email, @PathVariable String nombre, @PathVariable String id, @PathVariable String lugar, @PathVariable String registro, @PathVariable String vigencia, @PathVariable String qr) {
		return ticketService.enviarTicketVisitanteEmail(email, nombre, id, lugar, registro, vigencia, qr);
	}
	
	@GetMapping("enviar-ticket-invitado-email/{email}/{foto}/{nombre}/{id}/{lugar}/{registro}/{vigencia}/{qr}")
	public Respuesta enviarTicketInvitadoEmail(@PathVariable String email, @PathVariable String foto, @PathVariable String nombre, @PathVariable String id, @PathVariable String lugar, @PathVariable String registro, @PathVariable String vigencia, @PathVariable String qr) {
		return ticketService.enviarTicketInvitadoEmail(email, foto, nombre, id, lugar, registro, vigencia, qr);
	}

}
