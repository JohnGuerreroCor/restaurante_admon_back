package com.usco.edu.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IPiboteAdicionDao;
import com.usco.edu.entities.PiboteAdicion;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class PiboteAdicionDaoImpl implements IPiboteAdicionDao {

	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;

	@Autowired
	@Qualifier("JDBCTemplateConsulta")
	public JdbcTemplate jdbcTemplate;

    @Override
    public int crearPibote(String userdb, PiboteAdicion piboteAdicion) {
        String sql = "INSERT INTO sibusco.restaurante_pibote_adicion "
                + "(rco_codigo_general, rco_codigo_adicion, rpa_estado) "
                + "VALUES (?, ?, ?)";

        try (Connection connection = jdbcComponent.construirDataSourceDeUsuario(userdb).getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            connection.setAutoCommit(false);

            pstmt.setInt(1, piboteAdicion.getCodigoGeneral());
            pstmt.setInt(2, piboteAdicion.getCodigoAdicion());
            pstmt.setInt(3, piboteAdicion.getEstado());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                connection.rollback(); 
                return 0; 
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1); 
                    connection.commit(); 
                    return generatedId; 
                } else {
                    connection.rollback();
                    return 0;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }


    @Override
    public int actualizarPibote(String userdb, PiboteAdicion piboteAdicion) {
        String sql = "UPDATE sibusco.restaurante_pibote_adicion rpa "
                + "SET rpa.rpa_estado = ? "
                + "WHERE rpa.rco_codigo_general = ? "
                + "AND rpa.rco_codigo_adicion = ?;";

        try (Connection connection = jdbcComponent.construirDataSourceDeUsuario(userdb).getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false); 

            pstmt.setInt(1, piboteAdicion.getEstado());
            pstmt.setInt(2, piboteAdicion.getCodigoGeneral());
            pstmt.setInt(3, piboteAdicion.getCodigoAdicion());

            int affectedRows = pstmt.executeUpdate();

            connection.commit(); 
            return affectedRows; 

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
