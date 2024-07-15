package com.usco.edu.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Properties;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import com.usco.edu.dto.Email;

@Component
public class EmailComiteComponent {
	
	//@Value("${direccion_servidor}")
		//private String rutaServidor;
		
		Logger logger = LoggerFactory.getLogger(EmailComponent.class);
		
		public void enviar(Email email) {
			
			this.enviar(email, false);
		}
		
		public void enviar(Email email, boolean lanzarError) {
			
			MimeMessagePreparator messagePreparator = mimeMessage -> {

				MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
				messageHelper.setTo(email.getDestinatario());
				messageHelper.setSubject(email.getAsunto());
				
				String mje = this.build(email);
				messageHelper.setText( mje , true);
			};

			try {

				JavaMailSender emailSender = this.crearJavaMailSender();
				emailSender.send(messagePreparator);

			} catch (MailException e) {
				logger.error(email.toString());
				e.printStackTrace();
				
				if(lanzarError) throw e;
			}
		}
		
		
		private String build(Email email ) throws Exception {

			String plantillaCorreo = this
					.obtenerTextoDeArchivo(new ClassPathResource("static/plantilla_email/notificacion_invitacion_comite.html").getFile());
			
			plantillaCorreo = plantillaCorreo.replaceAll(":nombrePersona", email.getNombreDestinatario());
			plantillaCorreo = plantillaCorreo.replaceAll(":textoUno", email.getTextoUno());
			plantillaCorreo = plantillaCorreo.replaceAll(":textoDos", email.getTextoDos());
			plantillaCorreo = plantillaCorreo.replaceAll(":confidencial", email.getConfidencial());
			plantillaCorreo = plantillaCorreo.replaceAll(":titulo", email.getTitulo());
			plantillaCorreo = plantillaCorreo.replaceAll(":textoTres", email.getTextoTres());
			plantillaCorreo = plantillaCorreo.replaceAll(":footer", email.getFooter());
			plantillaCorreo = plantillaCorreo.replaceAll(":programa", email.getPrograma());
			//plantillaCorreo = plantillaCorreo.replaceAll(":rutaLogoUsco", rutaServidor + "img/universidad-surcolombiana.png");
			
			
			return plantillaCorreo;
		}
		
		
		
		private String obtenerTextoDeArchivo(File file) {

			FileReader fr = null;
			BufferedReader br = null;
			String contenido = "";
			try {
				fr = new FileReader(file.getPath());
				br = new BufferedReader(fr);
				String linea = "";
				while ((linea = br.readLine()) != null) {
					contenido += linea;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					br.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return contenido;
		}
		
		
		
		private JavaMailSender crearJavaMailSender() {

			JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
			UserPass email = this.getEmailRandom();
			// Using gmail
			mailSender.setHost("smtp.gmail.com");
			mailSender.setPort(587);
			mailSender.setUsername(email.user);
			mailSender.setPassword(email.pass);
			mailSender.setDefaultEncoding("UTF-8");

			Properties javaMailProperties = new Properties();
			javaMailProperties.put("mail.smtp.starttls.enable", "true");
			javaMailProperties.put("mail.smtp.auth", "true");
			javaMailProperties.put("mail.transport.protocol", "smtp");
			javaMailProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

			mailSender.setJavaMailProperties(javaMailProperties);

			return mailSender;
		}
		
		
		
		//---- metodo para seleccionar un correo de forma aleatoria
		private UserPass getEmailRandom() {

			UserPass emails[] = { 
					new UserPass("siac1@usco.edu.co","4credit4cion1"),
					new UserPass("siac2@usco.edu.co","4credit4cion2"),
					new UserPass("siac3@usco.edu.co","4credit4cion3"),
					new UserPass("siac4@usco.edu.co","4credit4cion4"),
					new UserPass("siac5@usco.edu.co","4credit4cion5"),
					};

			Random random = new Random();
			int n = random.nextInt(emails.length);

			UserPass email = emails[n];

			return email;
		}


		//---- clase para para establecer el usuario y contrasena del correo---//
		private class UserPass {
			
			String user;
			String pass;
			
			public UserPass(String user, String pass) {
				this.user = user;
				this.pass = pass;
			}
		}

}
