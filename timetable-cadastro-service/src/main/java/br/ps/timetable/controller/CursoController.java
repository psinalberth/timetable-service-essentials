package br.ps.timetable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@JsonView({Views.Curso.class})
	@RequestMapping(path="/", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Curso>> all() {
		return new ResponseEntity<Iterable<Curso>>(cursos.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(path="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Curso> byId(@PathVariable("id") int id) {
		
		Curso curso = cursos.findOne(id);
		
		if (curso != null)
			return new ResponseEntity<Curso>(curso, HttpStatus.OK);
					
		return new ResponseEntity<Curso>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(path="/", method={RequestMethod.POST, RequestMethod.PUT}, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Curso> save(@RequestBody Curso curso) {
		
		int id = curso.getId();
		
		if (id == 0)
			return new ResponseEntity<Curso>(cursos.save(curso), HttpStatus.CREATED);
		else if (id != 0)
			return new ResponseEntity<Curso>(cursos.save(curso), HttpStatus.OK);
		
		return ResponseEntity.badRequest().body(curso);
	}
	
	@RequestMapping(path="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Curso> delete(@PathVariable("id") int id) {
		cursos.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(path="/{id}/disponivel/{codigo}")
	public boolean isCodigoDisponivel(@PathVariable("id") int id, @PathVariable("codigo") int codigo) {
		
		Curso curso = cursos.findByCodigo(codigo);
		
		if (curso != null) {
			return curso.getId() == id;
		}
		
		return true;
	}
}
