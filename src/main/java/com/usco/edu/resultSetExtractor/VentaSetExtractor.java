package com.usco.edu.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.usco.edu.entities.Venta;
import com.usco.edu.rowMapper.VentaRowMapper;

public class VentaSetExtractor implements ResultSetExtractor<List<Venta>> {

    @Override
    public List<Venta> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Venta> list = new ArrayList<Venta>();
        while (rs.next()) {
            list.add(new VentaRowMapper().mapRow(rs, (rs.getRow() - 1)));
        }
        return list;
    }

}
