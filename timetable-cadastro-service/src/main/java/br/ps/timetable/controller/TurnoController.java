package br.ps.timetable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.ps.timetable.model.Turno;
import br.ps.timetable.repository.Turnos;

@RestController
@RequestMapping(path="turnos")
public class TurnoController {
	
	@Autowired
	private Turnos turnos;
	
	@RequestMapping(path="/", method=RequestMethod.GET)
	public Iterable<Turno> all() {
		return turnos.findAll();
	}
}
