package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Ticket;

public class TicketRowMapper implements RowMapper<Ticket> {
	
	@Override
	public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
		Ticket ticket = new Ticket();
		ticket.setCodigo(rs.getInt("tiv_codigo"));
		ticket.setTipoLugar(rs.getInt("tiv_tipo_lugar"));
		ticket.setFechaCreacion(rs.getTimestamp("tiv_fecha_creacion"));
		ticket.setFechaVigencia(rs.getTimestamp("tiv_fecha_vigencia"));
		ticket.setTipo(rs.getInt("tvt_codigo"));
		ticket.setEmailGraduado(rs.getInt("tiv_graduado"));
		ticket.setQr(rs.getString("tiv_qr"));
		ticket.setTercero(new TerceroRowMapper().mapRow(rs, rowNum));
		ticket.setPersona(new PersonaCarnetRowMapper().mapRow(rs, rowNum));
		ticket.setSede(new SedeCarnetRowMapper().mapRow(rs, rowNum));
		ticket.setSubSede(new SubSedeRowMapper().mapRow(rs, rowNum));
		ticket.setBloque(new BloqueRowMapper().mapRow(rs, rowNum));
		ticket.setOficina(new OficinaRowMapper().mapRow(rs, rowNum));
		return ticket;
	}
}
