package br.ps.timetable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.ps.timetable.model.Departamento;
import br.ps.timetable.repository.Departamentos;

@RestController
@RequestMapping(path="departamentos")
public class DepartamentoController {
	
	@Autowired
	private Departamentos departamentos;
	
	@RequestMapping(path="/", method=RequestMethod.GET)
	public Iterable<Departamento> all () {
		return departamentos.findAll();
	}
	
	@RequestMapping(path="/", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public void create(@RequestBody Departamento departamento) {
		departamentos.save(departamento);
	}
}
