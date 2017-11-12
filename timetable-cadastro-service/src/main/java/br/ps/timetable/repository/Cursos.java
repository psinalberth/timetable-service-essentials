package br.ps.timetable.repository;

import org.springframework.data.repository.CrudRepository;

import br.ps.timetable.model.Curso;

public interface Cursos extends CrudRepository<Curso, Integer> {

}
