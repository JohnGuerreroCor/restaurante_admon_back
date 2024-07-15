package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.Venta;

public interface IVentaDao {

    public List<Venta> obtenerVentasByPerCodigo(String userdb, int codigoPersona, int codigoContrato);

    public int registrarVenta(String userdb, Venta venta);

    public int actualizarVenta(String userdb, Venta venta);
}
