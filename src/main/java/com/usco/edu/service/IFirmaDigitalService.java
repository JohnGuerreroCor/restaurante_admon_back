package com.usco.edu.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.usco.edu.entities.Firma;
import com.usco.edu.entities.Rector;
import com.usco.edu.entities.Respuesta;

public interface IFirmaDigitalService {
	
	public List<Rector> buscarRectorActual(String userdb);
	
	public List<Firma> buscarFirmaActiva(String userdb);
	
	public List<Firma> buscarFirma(String userdb);
	
	void registrarFirma(Firma firma, String userdb);
	
	void actualizarFirmaEstado(Firma firma, String userdb);
	
	String subirArchivo(MultipartFile file, Long perCodigo, int uaa, String user, HttpServletRequest request);
	
	Respuesta enviarEmail(String email, String firma, String nombrePersona, String fecha, String nombre, String correo, String cargo);

	ByteArrayInputStream mirarArchivo(long archivoCodigo, String user, HttpServletResponse response);

}
