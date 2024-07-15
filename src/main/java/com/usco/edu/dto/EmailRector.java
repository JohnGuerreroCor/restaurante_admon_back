package com.usco.edu.dto;

public class EmailRector {
	
	private String asunto;
	private String email;
	private String firma;
	private String nombrePersona;
	private String fecha;
	private String incontec;
	private String nombre;
	private String correo;
	private String cargo;
	
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
	public String getFirma() {
		return firma;
	}
	public void setFirma(String firma) {
		this.firma = firma;
	}
	public String getNombrePersona() {
		return nombrePersona;
	}
	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public String getIncontec() {
		return incontec;
	}
	
	public void setIncontec(String incontec) {
		this.incontec = incontec;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	@Override
	public String toString() {
		return "EmailRector [asunto=" + asunto + ", email=" + email + ", firma=" + firma +  ", nombrePersona=" + nombrePersona + ", fecha=" + fecha + ", incontec=" + incontec + ", nombre=" + nombre + ", correo=" + correo + ", cargo=" + cargo + "]";
	}

}
