package com.usco.edu.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Usuario {

    private int id;
    private String username;
    private String password;
    private String userdb;
    private boolean state;
    private Uaa uaa;
    private Persona persona;
    private String role;
    private String horaInicioSesion;
    
}