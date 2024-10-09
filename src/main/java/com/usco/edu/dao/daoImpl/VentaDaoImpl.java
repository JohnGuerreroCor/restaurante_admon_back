package com.usco.edu.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IVentaDao;
import com.usco.edu.entities.Venta;
import com.usco.edu.resultSetExtractor.VentaSetExtractor;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class VentaDaoImpl implements IVentaDao {

    @Autowired
    private AuditoriaJdbcTemplate jdbcComponent;

    @Autowired
    @Qualifier("JDBCTemplateConsulta")
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
        Connection connection = null;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        String sql = "INSERT INTO sibusco.restaurante_venta "
                + "(per_codigo, rve_fecha_hora, rts_codigo, rco_codigo, rve_usuario, uaa_codigo, rve_estado) "
                + "VALUES (?, CONVERT(DATETIME, ?, 120), ?, ?, ?, ?, ?);";

        try {
            // Obtener la conexión manualmente
            connection = dataSource.getConnection();
            connection.setAutoCommit(false); // Desactivar auto-commit para manejar transacciones manualmente

            // Preparar el PreparedStatement con la conexión
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setLong(1, venta.getPersona().getCodigo());
            pstmt.setTimestamp(2, venta.getFechaHora());
            pstmt.setInt(3, venta.getTipoServicio().getCodigo());
            pstmt.setInt(4, venta.getContrato().getCodigo());
            pstmt.setLong(5, venta.getPersonaVentanilla().getCodigo());
            pstmt.setLong(6, venta.getUaa().getCodigo());
            pstmt.setInt(7, venta.getEstado());

            // Ejecutar la consulta
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                connection.rollback(); // Si no se afecta ninguna fila, hacer rollback
                throw new SQLException("No se pudo registrar la venta, no se afectaron filas.");
            }

            // Obtener la clave generada
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedId = generatedKeys.getInt(1);
                connection.commit(); // Hacer commit de la transacción si todo fue exitoso
                return generatedId;
            } else {
                connection.rollback(); // Hacer rollback si no se genera ninguna clave
                throw new SQLException("No se pudo obtener la clave generada para la venta.");
            }

        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback(); // Deshacer la transacción en caso de excepción
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return 0;
        } finally {
            if (connection != null) {
                try {
                    connection.close(); // Cerrar la conexión manualmente
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public int actualizarVenta(String userdb, Venta venta) {
        DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
        Connection connection = null;

        String sql = "UPDATE sibusco.restaurante_venta "
                + "SET per_codigo = ?, rve_fecha_hora = CONVERT(DATETIME, ?, 120), rts_codigo = ?, "
                + "rco_codigo = ?, rve_usuario = ?, uaa_codigo = ? "
                + "WHERE rve_codigo = ?";

        try {
            // Obtener la conexión manualmente
            connection = dataSource.getConnection();
            connection.setAutoCommit(false); // Desactivar auto-commit para manejar transacciones manualmente

            // Preparar el PreparedStatement con la conexión
            PreparedStatement pstmt = connection.prepareStatement(sql);

            // Asignar los parámetros
            pstmt.setLong(1, venta.getPersona().getCodigo());
            pstmt.setTimestamp(2, venta.getFechaHora());
            pstmt.setInt(3, venta.getTipoServicio().getCodigo());
            pstmt.setInt(4, venta.getContrato().getCodigo());
            pstmt.setLong(5, venta.getPersonaVentanilla().getCodigo());
            pstmt.setLong(6, venta.getUaa().getCodigo());
            pstmt.setInt(7, venta.getCodigo());

            // Ejecutar la actualización
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                connection.commit(); // Hacer commit de la transacción si todo fue exitoso
                return affectedRows;
            } else {
                connection.rollback(); // Hacer rollback si no se actualiza ninguna fila
                return 0;
            }

        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback(); // Deshacer la transacción en caso de excepción
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return 0;
        } finally {
            if (connection != null) {
                try {
                    connection.close(); // Cerrar la conexión manualmente
                } catch (SQLException e) {
                    e.printStackTrace();
                }
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
