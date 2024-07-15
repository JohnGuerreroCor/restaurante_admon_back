package com.usco.edu.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.IPiboteAdicionDao;
import com.usco.edu.entities.PiboteAdicion;
import com.usco.edu.service.IPiboteAdicionService;

@Service
public class PiboteAdicionServiceImpl implements IPiboteAdicionService {

	@Autowired
	private IPiboteAdicionDao PiboteAdicionDao;

	@Override
	public int crearPibote(String userdb, PiboteAdicion piboteAdicion) {
		System.out.println("crear piboteAdicion");
		System.out.println(piboteAdicion);
		return PiboteAdicionDao.crearPibote(userdb, piboteAdicion);
	}

	@Override
	public int actualizarPibote(String userdb, PiboteAdicion piboteAdicion) {
		System.out.println("actualizar piboteAdicion");
		System.out.println(piboteAdicion);
		return PiboteAdicionDao.actualizarPibote(userdb, piboteAdicion);
	}

}
