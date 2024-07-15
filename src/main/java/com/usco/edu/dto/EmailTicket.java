package com.usco.edu.dto;

public class EmailTicket {
	
	private String asunto;
	private String email;
	private String foto;
	private String nombreAplicativo;
	private String ticket;
	private String nombre;
	private String id;
	private String lugar;
	private String registro;
	private String vigencia;
	private String qr;
	
	public String getAsunto() {
		return asunto;
	}
	
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	public String getNombreAplicativo() {
		return nombreAplicativo;
	}
	
	public void setNombreAplicativo(String nombreAplicativo) {
		this.nombreAplicativo = nombreAplicativo;
	}
	
	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getLugar() {
		return lugar;
	}
	
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	
	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}
	
	public String getVigencia() {
		return vigencia;
	}
	
	public void setVigencia(String vigencia) {
		this.vigencia = vigencia;
	}
	
	public String getQr() {
		return qr;
	}

	public void setQr(String qr) {
		this.qr = qr;
	}
	
	
	@Override
	public String toString() {
		return "EmailTicket [asunto=" + asunto + ", email=" + email + ", foto=" + foto +  ", nombreAplicativo=" + nombreAplicativo +  ", ticket=" + ticket + ", nombre="
				+ nombre + ", id=" + id + ", lugar=" + lugar + ", registro=" + registro + ", vigencia=" + vigencia + ", qr=" + qr + "]";
	}

}
