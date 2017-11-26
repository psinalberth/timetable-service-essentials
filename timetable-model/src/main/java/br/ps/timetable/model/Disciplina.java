package br.ps.timetable.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonView;

import br.ps.timetable.view.Views;

@Entity
@Table(name="DISCIPLINA")
public class Disciplina extends Entidade {

	private static final long serialVersionUID = 3496962150746723949L;
	
	@Id
	@Column(name="ID_DISCIPLINA")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@NotBlank(message="A <b>sigla</b> é obrigatória.")
	@Column(name="SIGLA", length=10)
	private String sigla;
	
	@NotNull(message="O <b>código</b> é obrigatório.")
	@Column(name="CODIGO", columnDefinition="SMALLINT(3)")
	private Integer codigo;
	
	@NotBlank(message="A <b>descrição</b> é obrigatória.")
	@Column(name="DESCRICAO", length=100)
	private String descricao;
	
	@JsonView(Views.Lookup.class)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getSigla() {
		return sigla;
	}
	
	@JsonView(Views.Lookup.class)
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	@JsonView(Views.Lookup.class)
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		return this.getDescricao();
	}
}