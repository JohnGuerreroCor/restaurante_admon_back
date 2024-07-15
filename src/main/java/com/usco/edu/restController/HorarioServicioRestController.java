package com.usco.edu.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usco.edu.entities.HorarioServicio;
import com.usco.edu.service.IHorarioServicioService;

@RestController
@RequestMapping(path = "horarioServicio")
public class HorarioServicioRestController {

    @Autowired
    private IHorarioServicioService horarioServicioService;

    @GetMapping(path = "ejemplo")
    public boolean ejemplo() {

        return true;
    }

    @GetMapping(path = "obtener-horarioServicio/{username}/{codigo}")
    public List<HorarioServicio> obtenerHorarioServicio(@PathVariable String username, @PathVariable int codigo) {
        return horarioServicioService.obtenerHorarioServicio(username, codigo);
    }

    @GetMapping(path = "obtener-horarioServicio/{username}")
    public List<HorarioServicio> obtenerHorariosServicio(@PathVariable String username) {
        return horarioServicioService.obtenerHorariosServicio(username);
    }

    @PostMapping(path = "crear-horarioServicio/{username}")
    public int crearHorarioServicio(@PathVariable String username, @RequestBody HorarioServicio horarioServicio) {
        return horarioServicioService.crearHorarioServicio(username, horarioServicio);
    }

    @PutMapping(path = "actualizar-horarioServicio/{username}")
    public int actualizarHorarioServicio(@PathVariable String username, @RequestBody HorarioServicio horarioServicio) {
    	System.out.println(horarioServicio);
        return horarioServicioService.actualizarHorarioServicio(username, horarioServicio);
    }
}
