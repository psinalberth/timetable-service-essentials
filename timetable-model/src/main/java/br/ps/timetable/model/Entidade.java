package br.ps.timetable.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public abstract class Entidade implements Serializable {

	private static final long serialVersionUID = 109632090320375714L;

	@NotNull(message="O <b>usuário</b> é obrigatório.")
	@Column(name="USUARIO_ULT_ALTERACAO", length=21)
	@JsonIgnore
	private String usuarioUltAlteracao;
	
	@NotNull(message="A <b>data de alteração</b> é obrigatória.")
	@Column(name="DATA_ULT_ALTERACAO")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonIgnore
	private Date dataUltAlteracao;
	
	@NotNull
	@Version
	@Column(name="VERSAO", columnDefinition="TINYINT(3)")
	@JsonIgnore
	private int versao;
	
	public abstract int getId();
	
	public abstract void setId(int id);
	
	public String getUsuarioUltAlteracao() {
		return usuarioUltAlteracao;
	}
	
	public void setUsuarioUltAlteracao(String usuarioUltAlteracao) {
		this.usuarioUltAlteracao = usuarioUltAlteracao;
	}
	
	public Date getDataUltAlteracao() {
		return dataUltAlteracao;
	}
	
	public void setDataUltAlteracao(Date dataUltAlteracao) {
		this.dataUltAlteracao = dataUltAlteracao;
	}
	
	public int getVersao() {
		return versao;
	}
	
	public void setVersao(int versao) {
		this.versao = versao;
	}
	
	@PrePersist
	@PreUpdate
	public void prePersist() {
		this.setDataUltAlteracao(new Date());
		this.setUsuarioUltAlteracao("user");
	}
}