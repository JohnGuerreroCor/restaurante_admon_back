package com.usco.edu.service.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.ITicketDao;
import com.usco.edu.dto.EmailTicket;
import com.usco.edu.entities.Respuesta;
import com.usco.edu.entities.Ticket;
import com.usco.edu.service.ITicketService;
import com.usco.edu.util.EmailTicketComponent;
import com.usco.edu.util.EmailTicketInvitadoComponent;

@Service
public class TicketSeviceImpl implements ITicketService {
	
	@Autowired
	private ITicketDao ticketDao;
	
	@Autowired
	private EmailTicketComponent emailVisitanteComponent;
	
	@Autowired
	private EmailTicketInvitadoComponent emailInivitadoComponent;

	@Override
	public List<Ticket> obtenerTickets(int tipoTicket, String userdb) {
		
		return ticketDao.obtenerTickets(tipoTicket, userdb);
		
	}
	
	@Override
	public List<Ticket> obtenerTicketPorTerCodigo(int terCodigo, int tipoTicket, String userdb) {
		
		return ticketDao.obtenerTicketPorTerCodigo(terCodigo, tipoTicket, userdb);
		
	}

	@Override
	public List<Ticket> obtenerTicketPorPerCodigo(int perCodigo, int tipoTicket, String userdb) {
		
		return ticketDao.obtenerTicketPorPerCodigo(perCodigo, tipoTicket, userdb);
		
	}
	
	@Override
	public List<Ticket> obtenerTicketPorIdentificacion(String identificacion, String userdb) {
		
		return ticketDao.obtenerTicketPorIdentificacion(identificacion, userdb);
		
	}

	@Override
	public int registrar(String userdb, Ticket ticket) {
		
		return ticketDao.registrar(userdb, ticket);
		
	}
	


	@Override
	public Respuesta enviarTicketVisitanteEmail(String email, String nombre, String id, String lugar,
			String registro, String vigencia, String qr) {
		
		System.out.println("Entra por Email");
		
		Respuesta rta = new Respuesta();
		EmailTicket emailTicket = new EmailTicket();
		emailTicket.setAsunto("Ticket de Acceso Universidad Surcolombiana");
		emailTicket.setEmail(email);
		emailTicket.setNombreAplicativo("Sistema Administrador Carnetización");
		emailTicket.setNombre(nombre);
		emailTicket.setId(id);
		emailTicket.setLugar(lugar);
		emailTicket.setRegistro(registro);
		emailTicket.setVigencia(vigencia);
		emailTicket.setQr(qr);
		
		
		try {
			emailVisitanteComponent.enviar(emailTicket, true);
			rta.setEstado(true);
			return rta;
			
		}catch(Exception e) {
			e.printStackTrace();
			rta.setEstado(false);
			rta.setMensaje("El correo no pudo ser enviado");
			rta.setConsola("El correo no pudo ser enviado. Revisar log");
			return rta;
		}
	}

	@Override
	public Respuesta enviarTicketInvitadoEmail(String email, String foto, String nombre, String id,
			String lugar, String registro, String vigencia, String qr) {
		
		System.out.println("Entra por Email");
//		this.foto.url = this.foto.url.replace(/\+/g, '-').replace(/\//g, '_').replace(/\=+$/, '').replace(';', 'foto');
		foto = foto.replace("url", "?alt=media&token=");
		//foto = foto+"==";
		System.out.println(foto);
		
		Respuesta rta = new Respuesta();
		EmailTicket emailTicket = new EmailTicket();
		emailTicket.setAsunto("Ticket de Acceso Universidad Surcolombiana");
		emailTicket.setEmail(email);
		emailTicket.setFoto(foto);
		emailTicket.setNombreAplicativo("Sistema Administrador Carnetización");
		emailTicket.setNombre(nombre);
		emailTicket.setId(id);
		emailTicket.setLugar(lugar);
		emailTicket.setRegistro(registro);
		emailTicket.setVigencia(vigencia);
		emailTicket.setQr(qr);
		
		
		try {
			emailInivitadoComponent.enviar(emailTicket, true);
			rta.setEstado(true);
			return rta;
			
		}catch(Exception e) {
			e.printStackTrace();
			rta.setEstado(false);
			rta.setMensaje("El correo no pudo ser enviado");
			rta.setConsola("El correo no pudo ser enviado. Revisar log");
			return rta;
		}
		
	}

	
	/*
	@Override
	public int update(String userdb, Tercero tercero) {
		
		return terceroDao.update(userdb, tercero);
		
	}
	*/
}
