package com.usco.edu.service;

import java.util.List;

import com.usco.edu.entities.Bloque;
import com.usco.edu.entities.Oficina;
import com.usco.edu.entities.SedeCarnet;
import com.usco.edu.entities.SubSede;

public interface IUbicacionService {
	
	//LISTAR TODAS LAS CATEGORIAS
	
		public List<SedeCarnet> obtenerSedes(String userdb);
		
		public List<SubSede> obtenerSubSedes(String userdb);
		
		public List<Bloque> obtenerBloques(String userdb);
		
		public List<Oficina> obtenerOficinas(String userdb);
		
		//BUSCAR POR CATEGORIA PADRE A HIJA
		
		public List<SubSede> buscarSubSedePorSede(int codigo, String userdb);
		
		public List<Bloque> buscarBloquePorSubSede(int codigo, String userdb);
		
		public List<Oficina> buscarOficinaPorSede(int codigo, String userdb);

}
