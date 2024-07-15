package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.ISedeDao;
import com.usco.edu.entities.SedeCarnet;
import com.usco.edu.service.ISedeService;

@Service
public class SedeServiceImpl implements ISedeService{
	
	@Autowired
	private ISedeDao sedeDao;

	@Override
	public List<SedeCarnet> findAll() {
		return sedeDao.sedesAll();
	}

}
