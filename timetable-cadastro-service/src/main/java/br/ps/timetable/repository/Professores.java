package br.ps.timetable.repository;

import org.springframework.data.repository.CrudRepository;

import br.ps.timetable.model.Professor;

public interface Professores extends CrudRepository<Professor, Integer> {

}
