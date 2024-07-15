package com.usco.edu.service;

import java.util.List;

import com.usco.edu.entities.Venta;

public interface IVentaService {

	public List<Venta> obtenerVentasByPerCodigo(String userdb, int codigo, int codigoContrato);

    public int registrarVenta(String userdb, Venta venta);

    public int actualizarVenta(String userdb, Venta venta);
}
