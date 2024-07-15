package com.usco.edu.service;

import java.util.List;

import com.usco.edu.entities.Respuesta;
import com.usco.edu.entities.Ticket;

public interface ITicketService {
	
	public List<Ticket> obtenerTickets(int tipoTicket, String userdb);
	
	public List<Ticket> obtenerTicketPorTerCodigo(int terCodigo, int tipoTicket, String userdb);
	
	public List<Ticket> obtenerTicketPorPerCodigo(int perCodigo, int tipoTicket, String userdb);
	
	public List<Ticket> obtenerTicketPorIdentificacion(String identificacion, String userdb);
	
	int registrar(String userdb, Ticket ticket);
	
	Respuesta enviarTicketVisitanteEmail(String email, String nombre, String id, String lugar, String registro, String vigencia, String qr);
	
	Respuesta enviarTicketInvitadoEmail(String email, String foto, String nombre, String id, String lugar, String registro, String vigencia, String qr);

}
