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

import br.ps.timetable.model.Disciplina;
import br.ps.timetable.repository.Disciplinas;

@RestController
@RequestMapping(path="disciplinas")
public class DisciplinaController {
	
	@Autowired
	private Disciplinas disciplinas;
	
	@RequestMapping(path="/", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Disciplina>> all() {
		return new ResponseEntity<Iterable<Disciplina>>(disciplinas.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(path="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Disciplina> byId(@PathVariable("id") int id) {
		
		Disciplina disciplina = disciplinas.findOne(id);
		
		if (disciplina != null)
			return new ResponseEntity<Disciplina>(disciplina, HttpStatus.OK);
					
		return new ResponseEntity<Disciplina>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(path="/", method={RequestMethod.POST, RequestMethod.PUT}, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Disciplina> save(@RequestBody Disciplina disciplina) {
		
		int id = disciplina.getId();
		
		if (id == 0)
			return new ResponseEntity<Disciplina>(disciplinas.save(disciplina), HttpStatus.CREATED);
		else if (id != 0)
			return new ResponseEntity<Disciplina>(disciplinas.save(disciplina), HttpStatus.OK);
		
		return ResponseEntity.badRequest().body(disciplina);
	}
	
	@RequestMapping(path="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Disciplina> delete(@PathVariable("id") int id) {
		disciplinas.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(path="/{id}/disponivel/{codigo}")
	public boolean isCodigoDisponivel(@PathVariable("id") int id, @PathVariable("codigo") int codigo) {
		
		Disciplina disciplina = disciplinas.findByCodigo(codigo);
		
		if (disciplina != null) {
			return disciplina.getId() == id;
		}
		
		return true;
	}
}