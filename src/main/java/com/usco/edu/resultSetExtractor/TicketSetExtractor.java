package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Ticket;
import com.usco.edu.rowMapper.TicketRowMapper;

public class TicketSetExtractor  implements ResultSetExtractor<List<Ticket>> {
	
	@Override
	public List<Ticket> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Ticket> list = new ArrayList<Ticket>();
		while (rs.next()) {
			list.add(new TicketRowMapper().mapRow(rs, (rs.getRow() - 1)));
		}
		return list;
	}
}
