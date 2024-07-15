package com.usco.edu.service.serviceImpl;

import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usco.edu.dao.IDocumentoDao;
import com.usco.edu.dao.IFirmaDigitalDao;
import com.usco.edu.dto.EmailRector;
import com.usco.edu.dto.RespuestaSubirArchivo;
import com.usco.edu.dto.RespuestaVerArchivo;
import com.usco.edu.entities.Documento;
import com.usco.edu.entities.Firma;
import com.usco.edu.entities.Rector;
import com.usco.edu.entities.Respuesta;
import com.usco.edu.feing.EnviarArchivoClient;
import com.usco.edu.service.IFirmaDigitalService;
import com.usco.edu.util.EmailFirmaDigitalRectorComponent;

@Service
public class FirmaDigitalServiceImpl implements IFirmaDigitalService {
	
	@Autowired
	private IFirmaDigitalDao firmaDao;
	
	@Autowired
	private IDocumentoDao documentoDao;
	
	@Autowired
	private EnviarArchivoClient enviarArchivo;
	
	@Autowired
	private EmailFirmaDigitalRectorComponent emailComponent;

	@Override
	public List<Rector> buscarRectorActual(String userdb) {
		
		return firmaDao.buscarRectorActual(userdb);
		
	}
	
	@Override
	public List<Firma> buscarFirmaActiva(String userdb) {
		
		return firmaDao.buscarFirmaActiva(userdb);
		
	}
	
	@Override
	public List<Firma> buscarFirma(String userdb) {

		return firmaDao.buscarFirma(userdb);
		
	}

	@Override
	public void registrarFirma(Firma firma, String userdb) {
		
		firmaDao.registrarFirma(firma, userdb);
		
	}
	
	@Override
	public void actualizarFirmaEstado(Firma firma, String userdb) {
		
		firmaDao.actualizarFirmaEstado(firma, userdb);
		
	}

	@Override
	public String subirArchivo(MultipartFile file, Long perCodigo, int uaa, String user, HttpServletRequest request) {
		
		String err = "";
		if (!file.isEmpty()) {

			if (isValido(file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf(".")))) {

				Documento documento = new Documento();
				documento.setDocNombreArchivo(file.getOriginalFilename());
				documento.setPerCodigo("" + perCodigo);
				documento.setDocCliente("Carnetizacion_Administrador_VERSION:1.0");
				documento.setDocContenido("Firma Digital Carnetizacion");
				documento.setDocExtension("png");
				documento.setDocIp(request.getRemoteAddr());
				documento.setDocSesion(request.getSession().getId());
				documento.setModCodigo(35);//CAMBIAR PARA PRODUCCION
				documento.setTdocCodigo(38);//CAMBIAR PARA PRODUCCION
				documento.setUaaCodigo(uaa);

				String Key = documentoDao.getKeyDocumento(
						documento.getModCodigo().toString() + documento.getUaaCodigo() + documento.getPerCodigo(),
						user);

				System.out.println("datos: " + documento.getModCodigo().toString() + documento.getUaaCodigo()
						+ documento.getPerCodigo());
				ObjectMapper objectMapper = new ObjectMapper();
				RespuestaSubirArchivo respuesta = new RespuestaSubirArchivo();
				try {
					respuesta = enviarArchivo.subirArchivo(file, Key, objectMapper.writeValueAsString(documento));
					System.out.println(respuesta.getMensaje());
					System.out.println(respuesta.getIdDocumento());
					err = respuesta.getMensaje();
					if (!respuesta.isEstado()) {

						System.out.println("Ocurrio un error: " + respuesta.getMensaje());
					}

				} catch (Exception e) {

					System.out.println("Ocurrio un error: " + e);
					return null;
				}
				System.out.println("Creado" + respuesta.getMensaje());
				return respuesta.getIdDocumento() + "";

			} else {

				System.out.println("Ocurrio un error" + err);
				return null;
			}

		} else {
			System.out.println("Ocurrio un error" + err);
			return null;
		}
	}

	@Override
	public Respuesta enviarEmail(String email, String firma, String nombrePersona, String fecha, String nombre, String correo, String cargo) {
		
		System.out.println("Entra por Email");
		Respuesta rta = new Respuesta();
		EmailRector emailRector = new EmailRector();
		firma = firma.replace("url", "?alt=media&token=");
		//foto = foto+"==";
		System.out.println(firma);
		emailRector.setAsunto("Notificación registro o actualizacion firma digital - Carnet digital");
		emailRector.setEmail(email);
		emailRector.setFirma(firma);
		emailRector.setNombrePersona(nombrePersona);
		emailRector.setFecha(fecha);
		emailRector.setNombre(nombre);
		emailRector.setCorreo(correo);
		emailRector.setCargo(cargo);
		
		
		try {
			emailComponent.enviar(emailRector, true);
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
	public ByteArrayInputStream mirarArchivo(long archivoCodigo, String user, HttpServletResponse response) {
		String Key = documentoDao.getKeyDocumento(archivoCodigo + "", user);

		RespuestaVerArchivo respuesta = new RespuestaVerArchivo();

		try {
			respuesta = enviarArchivo.mostrarArchivo(archivoCodigo, Key);

			byte[] archivoBytes = Base64.getDecoder().decode(respuesta.getBase64().split(",")[1]);
			return new ByteArrayInputStream(archivoBytes);

		} catch (Exception e) {
			System.out.println(e);
		}

		return null;
	}
	
	public boolean isValido(String nombre) {
		String expresion = "^([[a-zA-Z][0-9]_]{2,150})$";
		try {
			Pattern p = Pattern.compile(expresion);
			Matcher matcher = p.matcher(nombre);
			return matcher.matches();
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}

	}

}
