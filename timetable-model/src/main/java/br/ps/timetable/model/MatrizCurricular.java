package br.ps.timetable.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import br.ps.timetable.view.Views;

@Entity
@Table(name="MATRIZ_CURRICULAR")
public class MatrizCurricular extends Entidade {

	private static final long serialVersionUID = -932527619399754046L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_MATRIZ")
	@JsonView(Views.Public.class)
	private int id;
	
	@NotNull(message="O <b>ano</b> é obrigatório.")
	@Column(name="ANO", columnDefinition="SMALLINT(4)")
	@JsonView(Views.Public.class)
	private Integer ano;
	
	@NotNull(message="A quantidade de <b>semestres</b> é obrigatória.")
	@Column(name="SEMESTRES", columnDefinition="TINYINT(2)")
	private Integer semestres;
	
	@NotNull(message="O <b>curso</b> é obrigatório.")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_CURSO")
	@JsonView(Views.Compound.class)
	private Curso curso;
	
	@NotNull(message="O <b>turno</b> é obrigatório.")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_TURNO")
	@JsonView(Views.Compound.class)
	private Turno turno;
	
	@Valid
	@Size(min=1, message="Nenhum <b>período</b> adicionado.")
	@OneToMany(fetch=FetchType.LAZY, mappedBy="matrizCurricular", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Periodo> periodos = new ArrayList<Periodo>();
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Turno getTurno() {
		return turno;
	}
	
	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public Integer getSemestres() {
		return semestres;
	}

	public void setSemestres(Integer semestres) {
		this.semestres = semestres;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	public List<Periodo> getPeriodos() {
		return periodos;
	}
	
	public void setPeriodos(List<Periodo> periodos) {
		this.periodos = periodos;
	}
	
	@Override
	public String toString() {
		return getCurso().getCodigo() + getAno() + getTurno().getCodigo();
	}
}