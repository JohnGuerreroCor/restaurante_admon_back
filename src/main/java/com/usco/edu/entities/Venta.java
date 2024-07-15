package com.usco.edu.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Venta implements Serializable {

    private int codigo;
    private Persona persona;
    private Timestamp fechaHora;
    private TipoServicio tipoServicio;
    private Contrato contrato;
    private Persona personaVentanilla;
    private Uaa uaa;
    private int estado;

    private static final long serialVersionUID = 1L;
}
