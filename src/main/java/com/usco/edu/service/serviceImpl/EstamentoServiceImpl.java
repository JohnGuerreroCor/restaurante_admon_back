package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.IEstamentoDao;
import com.usco.edu.entities.Estamento;
import com.usco.edu.service.IEstamentoService;

@Service
public class EstamentoServiceImpl implements IEstamentoService {
	
	@Autowired
	private IEstamentoDao dao;
	
	@Override
	public List<Estamento> estamentos(String userdb) {
		// TODO Auto-generated method stub
		return dao.estamentos(userdb);
	}

}
