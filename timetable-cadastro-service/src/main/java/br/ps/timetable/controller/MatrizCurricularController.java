package br.ps.timetable.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import br.ps.timetable.model.DetalheDisciplina;
import br.ps.timetable.model.MatrizCurricular;
import br.ps.timetable.model.Periodo;
import br.ps.timetable.repository.MatrizesCurriculares;
import br.ps.timetable.view.Views;

@RestController
@RequestMapping(path="matrizes")
public class MatrizCurricularController {
	
	@Autowired
	private MatrizesCurriculares matrizesCurriculares;
	
	@RequestMapping(path="/", method=RequestMethod.GET)
	@JsonView(Views.MatrizCurricular.class)
	public Iterable<MatrizCurricular> all () {
		return matrizesCurriculares.findAll();
	}
	
	@RequestMapping(path="/{id}", method=RequestMethod.GET)
	@JsonView(Views.MatrizCurricular.class)
	public MatrizCurricular byId(@PathVariable("id") int id) {
		return matrizesCurriculares.findOne(id);
	}
	
	@RequestMapping(path="/{id}/periodos", method=RequestMethod.GET)
	@JsonView(Views.MatrizCurricular.class)
	public Iterable<Periodo> getPeriodos(@PathVariable("id") int id) {
		return matrizesCurriculares.findOne(id).getPeriodos();
	}
	
	@JsonView(Views.MatrizCurricular.class)
	@RequestMapping(path="/{matriz}/periodos/{periodo}/detalhes")
	public List<DetalheDisciplina> getDetalhes(@PathVariable("matriz") int matrizId, @PathVariable("periodo") int periodoId) {
		return matrizesCurriculares.findOne(matrizId).getPeriodos().get(periodoId-1).getDetalhes();
	}
	
	@JsonView(Views.MatrizCurricular.class)
	@RequestMapping(path="/{matriz}/periodos/{periodo}/detalhes/{detalhe}")
	public DetalheDisciplina getDisciplina(@PathVariable("matriz") int matrizId, @PathVariable("periodo") int periodoId, 
			                               @PathVariable("detalhe") int detalheId) {
		return matrizesCurriculares.findOne(matrizId).getPeriodos().get(periodoId-1).getDetalhes().stream().filter(detalhe -> detalhe.getId() == detalheId).findAny().get();
	}
}