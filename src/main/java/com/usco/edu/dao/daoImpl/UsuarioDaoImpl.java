package com.usco.edu.dao.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IUsuarioDao;
import com.usco.edu.entities.Usuario;
import com.usco.edu.rowMapper.UsuarioRowMapper;

@Repository
public class UsuarioDaoImpl implements IUsuarioDao{
	
	@Autowired
	@Qualifier("JDBCTemplateLogin")
	public JdbcTemplate jdbcTemplate;
	
	//REEMPLAZAR LA VISTA DE USURIO POR LA QUE IMPLEMENTA SU APLICATIVO, TENER EN CUENTA LAS VARIBALES DE LA CONSULTA IMPLEMENTADA EN EL ROWMAPPER
 
	@Override
	public Usuario buscarUsuario(String username) {
		
		String sql = "select *, GETDATE() as horaInicioSesion from usuario_sibusco_restaurante_login usrl "
				+ "inner join uaa u on u.uaa_codigo = usrl.usg_uaa "
				+ "inner join sede s on s.sed_codigo = u.sed_codigo "
				+ "inner join persona p on p.per_codigo = usrl.up "
				+ "where  usrl.us = ? "
				+ "and u.uaa_codigo = 645 "
				+ "and usrl.gru_codigo = 88;";
		
		return jdbcTemplate.queryForObject(sql, new Object[] { username }, new UsuarioRowMapper());
	}

	//REEMPLAZAR LA VISTA DE USURIO POR LA QUE IMPLEMENTA SU APLICATIVO, TENER EN CUENTA LAS VARIBALES DE LA CONSULTA IMPLEMENTADA EN EL ROWMAPPER

	@Override
	public boolean validarUsuario(String username) {
		int result = 0;
		String sql = "select COUNT(usrl.us) from usuario_sibusco_restaurante_login usrl "
				+ "inner join uaa u on u.uaa_codigo = usrl.usg_uaa "
				+ "inner join sede s on s.sed_codigo = u.sed_codigo "
				+ "inner join persona p on p.per_codigo = usrl.up "
				+ "where  usrl.us = ? and usrl.gru_codigo = 88";
		result =  jdbcTemplate.queryForObject(sql, new Object[] { username }, Integer.class);
		return result > 0 ? true : false;
	}
	
	

}
