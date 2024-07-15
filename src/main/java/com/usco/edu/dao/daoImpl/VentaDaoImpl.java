package com.usco.edu.dao.daoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.IVentaDao;
import com.usco.edu.entities.Venta;
import com.usco.edu.resultSetExtractor.HorarioServicioSetExtractor;
import com.usco.edu.resultSetExtractor.VentaSetExtractor;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class VentaDaoImpl implements IVentaDao {

    @Autowired
    private AuditoriaJdbcTemplate jdbcComponent;

    @Autowired
    @Qualifier("JDBCTemplatePlanesConsulta")
    public JdbcTemplate jdbcTemplate;

    @Override
    public List<Venta> obtenerVentasByPerCodigo(String userdb, int codigoPersona, int codigoContrato) {
        String sql = "SELECT * FROM sibusco.restaurante_venta rev "
        		+ "INNER JOIN dbo.persona p ON p.per_codigo = rev.per_codigo "
        		+ "INNER JOIN sibusco.restaurante_tipo_servicio rts ON rts.rts_codigo = rev.rts_codigo "
        		+ "INNER JOIN sibusco.restaurante_contrato rc ON rc.rco_codigo = rev.rco_codigo "
        		+ "INNER JOIN dbo.persona p2 ON p2.per_codigo = rev.per_codigo "
        		+ "INNER JOIN dbo.uaa u ON u.uaa_codigo = rev.uaa_codigo "
        		+ "WHERE rev.per_codigo = " + codigoPersona + " " 
        		+ "AND rev.rco_codigo = " + codigoContrato;
        return jdbcTemplate.query(sql, new VentaSetExtractor());
    }

    @Override
    public int registrarVenta(String userdb, Venta venta) {
        DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
        NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO sibusco.restaurante_venta "
                + "(per_codigo, rve_fecha_hora, rts_codigo, rco_codigo, rve_usuario, uaa_codigo, rve_estado) "
                + "VALUES (:persona,  CONVERT(DATETIME, :fechaHora, 120), :tipoServicio, :contrato, :personaVentanilla, :uaa, :venta);";

        try {

            MapSqlParameterSource parameter = new MapSqlParameterSource();

            parameter.addValue("persona", venta.getPersona().getCodigo());
            parameter.addValue("fechaHora", venta.getFechaHora());
            parameter.addValue("tipoServicio", venta.getTipoServicio().getCodigo());
            parameter.addValue("contrato", venta.getContrato().getCodigo());
            parameter.addValue("personaVentanilla", venta.getPersonaVentanilla().getCodigo());
            parameter.addValue("uaa", venta.getUaa().getCodigo());
            parameter.addValue("estado", venta.getEstado());

            jdbc.update(sql, parameter, keyHolder);
            return keyHolder.getKey().intValue();

        } catch (Exception e) {

            e.printStackTrace();
            return 0;
        } finally {
            try {
                cerrarConexion(dataSource.getConnection());
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public int actualizarVenta(String userdb, Venta venta) {
        DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
        NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

        String sql = "UPDATE sibusco.restaurante_venta "
                + "SET per_codigo:persona, rve_fecha_hora:fechaHora, rts_codigo:tipoServicio, rco_codigo:contrato, rve_usuario:personaVentanilla, uaa_codigo:uaa "
                + "WHERE rve_codigo=:codigo";

        try {

            MapSqlParameterSource parameter = new MapSqlParameterSource();

            parameter.addValue("codigo", venta.getCodigo());
            parameter.addValue("persona", venta.getPersona().getCodigo());
            parameter.addValue("fechaHora", venta.getFechaHora());
            parameter.addValue("tipoServicio", venta.getTipoServicio().getCodigo());
            parameter.addValue("contrato", venta.getContrato().getCodigo());
            parameter.addValue("personaVentanilla", venta.getPersonaVentanilla().getCodigo());
            parameter.addValue("uaa", venta.getUaa().getCodigo());

            return jdbc.update(sql, parameter);
        } catch (Exception e) {

            e.printStackTrace();
            return 0;
        } finally {
            try {
                cerrarConexion(dataSource.getConnection());
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void cerrarConexion(Connection con) {
        if (con == null)
            return;

        try {
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
