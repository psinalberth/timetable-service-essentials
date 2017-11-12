package br.ps.timetable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import br.ps.timetable.model.Disciplina;
import br.ps.timetable.model.Professor;
import br.ps.timetable.repository.Professores;
import br.ps.timetable.view.Views;

@RestController
@RequestMapping(path="professores")
public class ProfessorController {
	
	@Autowired
	private Professores professores;
	
	@RequestMapping(path="/", method=RequestMethod.GET)
	@JsonView({Views.Compound.class})
	public Iterable<Professor> all() {
		return professores.findAll();
	}
	
	@RequestMapping(path="/{id}", method=RequestMethod.GET)
	public Professor byId(@PathVariable("id") int id) {
		return professores.findOne(id);
	}
	
	@RequestMapping(path="/{id}/disciplinas", method=RequestMethod.GET)
	public Iterable<Disciplina> byProfessor(@PathVariable("id") int id) {
		return professores.findOne(id).getDisciplinasLecionaveis();
	}
}
