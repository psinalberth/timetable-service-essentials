package br.ps.timetable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import br.ps.timetable.model.Curso;
import br.ps.timetable.repository.Cursos;
import br.ps.timetable.view.Views;

@RestController
@RequestMapping(path="cursos")
public class CursoController {
	
	@Autowired
	private Cursos cursos;
	
	@RequestMapping(path="/", method=RequestMethod.GET)
	@JsonView({Views.Compound.class})
	public Iterable<Curso> all () {
		return cursos.findAll();
	}
	
	@RequestMapping(path="/", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public void create(@RequestBody Curso curso) {
		cursos.save(curso);
	}
}
