package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.Ticket;

public interface ITicketDao {
	
	public List<Ticket> obtenerTickets(int tipoTicket, String userdb);
	
	public List<Ticket> obtenerTicketPorTerCodigo(int terCodigo, int tipoTicket, String userdb);
	
	public List<Ticket> obtenerTicketPorPerCodigo(int perCodigo, int tipoTicket, String userdb);
	
	public List<Ticket> obtenerTicketPorIdentificacion(String identificacion, String userdb);
	
	public int registrar(String userdb, Ticket ticket);
	
	/*int actualizar(String userdb, Ticket ticket);*/

}
