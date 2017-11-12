package br.ps.timetable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.ps.timetable.model.Disciplina;
import br.ps.timetable.repository.Disciplinas;

@RestController
@RequestMapping(path="disciplinas")
public class DisciplinaController {
	
	@Autowired
	private Disciplinas disciplinas;
	
	@RequestMapping(path="/", method=RequestMethod.GET)
	public Iterable<Disciplina> all() {
		return disciplinas.findAll();
	}
	
	@RequestMapping(path="/{id}", method=RequestMethod.GET)
	public Disciplina byId(@PathVariable("id") int id) {
		return disciplinas.findOne(id);
	}
	
	@RequestMapping(path="/", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public void create(@RequestBody Disciplina disciplina) {
		disciplinas.save(disciplina);
	}
	
	@RequestMapping(path="/{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable("id") int id) {
		disciplinas.delete(id);
	}
}
