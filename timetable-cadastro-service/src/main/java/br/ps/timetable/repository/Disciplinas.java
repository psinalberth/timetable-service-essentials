package br.ps.timetable.repository;

import org.springframework.data.repository.CrudRepository;

import br.ps.timetable.model.Disciplina;

public interface Disciplinas extends CrudRepository<Disciplina, Integer> {
 
	public Disciplina findByCodigo(int codigo);
}
