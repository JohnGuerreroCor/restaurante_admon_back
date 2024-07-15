package com.usco.edu.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Properties;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import com.usco.edu.dto.EmailTicket;

@Component
public class EmailTicketInvitadoComponent {
	
	Logger logger = LoggerFactory.getLogger(EmailComponent.class);
	
	public void enviar(EmailTicket email) {
		
		this.enviar(email, false);
	}
	
	public void enviar(EmailTicket email, boolean lanzarError) {
		
		MimeMessagePreparator messagePreparator = mimeMessage -> {

			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setTo(email.getEmail());
			messageHelper.setFrom("carnetizacion1@usco.edu.co");
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
	
	
	private String build(EmailTicket email ) throws Exception {

		String plantillaCorreo = this
				.obtenerTextoDeArchivo(new ClassPathResource("static/plantilla_email/plantilla-ticket-invitado-email.html").getFile());
		
		plantillaCorreo = plantillaCorreo.replaceAll(":email", email.getEmail());
		plantillaCorreo = plantillaCorreo.replaceAll(":foto", email.getFoto());
		plantillaCorreo = plantillaCorreo.replaceAll(":nombre", email.getNombre());
		plantillaCorreo = plantillaCorreo.replaceAll(":id", email.getId());
		plantillaCorreo = plantillaCorreo.replaceAll(":lugar", email.getLugar());
		plantillaCorreo = plantillaCorreo.replaceAll(":registro", email.getRegistro());
		plantillaCorreo = plantillaCorreo.replaceAll(":vigencia", email.getVigencia());
		plantillaCorreo = plantillaCorreo.replaceAll(":qr", email.getQr());
		
		
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
				new UserPass("carnetizacion1@usco.edu.co","c4rn3t1z4c10n"),
				new UserPass("carnetizacion2@usco.edu.co","c4rn3t1z4c10n"),
				new UserPass("carnetizacion3@usco.edu.co","c4rn3t1z4c10n"),
				new UserPass("carnetizacion4@usco.edu.co","c4rn3t1z4c10n"),
				new UserPass("carnetizacion5@usco.edu.co","c4rn3t1z4c10n"),
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
