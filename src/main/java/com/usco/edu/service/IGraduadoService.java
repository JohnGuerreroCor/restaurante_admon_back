package com.usco.edu.service;
import java.util.List;

import com.usco.edu.entities.Graduado;

public interface IGraduadoService {
	public List<Graduado> buscarPorCodigo(String codigo, String userdb);
}
