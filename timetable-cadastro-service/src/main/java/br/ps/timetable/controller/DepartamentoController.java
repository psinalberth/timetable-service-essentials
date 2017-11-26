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

import br.ps.timetable.model.Departamento;
import br.ps.timetable.repository.Departamentos;

@RestController
@RequestMapping(path="departamentos")
public class DepartamentoController {
	
	@Autowired
	private Departamentos departamentos;
	
	@RequestMapping(path="/", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Departamento>> all() {
		return new ResponseEntity<Iterable<Departamento>>(departamentos.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(path="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Departamento> byId(@PathVariable("id") int id) {
		
		Departamento departamento = departamentos.findOne(id);
		
		if (departamento != null)
			return new ResponseEntity<Departamento>(departamento, HttpStatus.OK);
					
		return new ResponseEntity<Departamento>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(path="/", method={RequestMethod.POST, RequestMethod.PUT}, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Departamento> save(@RequestBody Departamento departamento) {
		
		int id = departamento.getId();
		
		if (id == 0)
			return new ResponseEntity<Departamento>(departamentos.save(departamento), HttpStatus.CREATED);
		else if (id != 0)
			return new ResponseEntity<Departamento>(departamentos.save(departamento), HttpStatus.OK);
		
		return ResponseEntity.badRequest().body(departamento);
	}
	
	@RequestMapping(path="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Departamento> delete(@PathVariable("id") int id) {
		departamentos.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(path="/{id}/disponivel/{codigo}")
	public boolean isCodigoDisponivel(@PathVariable("id") int id, @PathVariable("codigo") int codigo) {
		
		Departamento departamento = departamentos.findByCodigo(codigo);
		
		if (departamento != null) {
			return departamento.getId() == id;
		}
		
		return true;
	}
}
