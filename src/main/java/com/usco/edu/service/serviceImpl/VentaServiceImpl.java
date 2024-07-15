package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.IVentaDao;
import com.usco.edu.entities.Venta;
import com.usco.edu.service.IVentaService;

@Service
public class VentaServiceImpl implements IVentaService {

	@Autowired
	private IVentaDao VentaDao;

	@Override
	public List<Venta> obtenerVentasByPerCodigo(String userdb, int codigoPersona, int codigoContrato) {
		return this.VentaDao.obtenerVentasByPerCodigo(userdb, codigoPersona, codigoContrato);
	}

	@Override
	public int registrarVenta(String userdb, Venta venta) {
		return this.VentaDao.registrarVenta(userdb, venta);
	}

	@Override
	public int actualizarVenta(String userdb, Venta venta) {
		return this.VentaDao.actualizarVenta(userdb, venta);
	}

}
