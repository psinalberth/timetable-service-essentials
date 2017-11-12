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

import com.fasterxml.jackson.annotation.JsonView;

import br.ps.timetable.view.Views;

@Entity
@Table(name="PERIODO")
public class Periodo extends Entidade {

	private static final long serialVersionUID = -1031109365878584073L;
	
	@Id
	@Column(name="ID_PERIODO")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@NotNull(message="O código é obrigatório.")
	@Column(name="CODIGO", columnDefinition="TINYINT(2)")
	private Integer codigo;
	
	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_MATRIZ")
	private MatrizCurricular matrizCurricular;
	
	@Valid
	@OneToMany(fetch=FetchType.LAZY, mappedBy="periodo", cascade=CascadeType.ALL)
	private List<DetalheDisciplina> detalhes = new ArrayList<DetalheDisciplina>();
	
	@JsonView(Views.Compound.class)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@JsonView(Views.Compound.class)
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public MatrizCurricular getMatrizCurricular() {
		return matrizCurricular;
	}
	
	public void setMatrizCurricular(MatrizCurricular matrizCurricular) {
		this.matrizCurricular = matrizCurricular;
	}

	public List<DetalheDisciplina> getDetalhes() {
		return detalhes;
	}
	
	public void setDetalhes(List<DetalheDisciplina> detalhes) {
		this.detalhes = detalhes;
	}
	
	@JsonView(Views.Compound.class)
	public int getTamanho() {
		return detalhes.size();
	}
}