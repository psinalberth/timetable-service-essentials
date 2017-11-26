package br.ps.timetable.tests;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.ps.timetable.model.Curso;
import br.ps.timetable.model.Departamento;
import br.ps.timetable.model.MatrizCurricular;
import br.ps.timetable.model.Turno;
import br.ps.timetable.view.Views;

public class CursoTest {
	
	ObjectMapper mapper;
	
	Curso curso;
	Departamento departamento;
	MatrizCurricular matrizCurricular;
	Turno turno;
	
	@Before
	public void setUp() throws Exception {
		
		mapper = new ObjectMapper();
		mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
		
		curso = new Curso();
		
		departamento = new Departamento();
		departamento.setId(992);
		departamento.setCodigo("DCOMP");
		departamento.setNome("Departamento de Computação");
		
		curso.setId(23);
		curso.setCodigo("SI");
		curso.setNome("Sistemas de Informação");
		curso.setDepartamento(departamento);
		
		turno = new Turno();
		turno.setId(290);
		turno.setCodigo("VESP/NOT");
		turno.setNome("Vespertino/Noturno");
		
		matrizCurricular = new MatrizCurricular();
		matrizCurricular.setId(84);
		matrizCurricular.setAno(2017);
		matrizCurricular.setSemestres(8);
		matrizCurricular.setCurso(curso);
		matrizCurricular.setTurno(turno);
	}
	
	@Test
	public void testSerial() throws JsonProcessingException {
				
		String result = mapper
			.writerWithView(Views.Curso.class)
			.writeValueAsString(curso)
			.replaceAll(",", ",\n")
			.replaceAll(":", ": ")
			.replaceAll("\\{", "\\{\n");
		
		System.out.println("teste2="+result);
	}
	
	@Test
	public void testeMatriz() throws JsonProcessingException {
				
		String result = mapper
				.writerWithView(Views.MatrizCurricular.class)
				.writeValueAsString(matrizCurricular)
				.replaceAll(",", ",\n")
				.replaceAll(":", ": ")
				.replaceAll("\\{", "\\{\n");
			
			System.out.println("teste1="+result);
	}
}
