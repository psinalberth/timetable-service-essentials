package br.ps.timetable.view;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Classe destinada a definir o nível de visibilidade de propriedades, objetos e métodos utilizando a API Jackson, 
 * através da anotação {@linkplain JsonView}.
 * 
 * @author inalberth
 * @see JsonView
 */
public class Views {
	
	/**
	 * Interface base, comum a todas as <i>views</i>, encarregada pela exibição de apenas informações essenciais
	 * de classes dentro de outras classes, como {@code id}, {@code nome}, etc
	 */
	public interface Lookup {}
	
	/**
	 * <i>View</i> destinada a exibir informações sob a perspectiva da classe {@linkplain br.ps.timetable.model.Curso}.
	 * 
	 * @see br.ps.timetable.model.Curso
	 */
	public interface Curso extends Lookup {}
	
	/**
	 * <i>View</i> destinada a exibir informações sob a perspectiva da classe {@linkplain br.ps.timetable.model.Professor}.
	 * 
	 * @see br.ps.timetable.model.Professor
	 */
	public interface Professor extends Lookup {}
	
	/**
	 * <i>View</i> destinada a exibir informações sob a perspectiva da classe {@linkplain br.ps.timetable.model.MatrizCurricular}.
	 * 
	 * @see br.ps.timetable.model.MatrizCurricular
	 */
	public interface MatrizCurricular extends Lookup {}
	
	/**
	 * <i>View</i> destinada a exibir informações sob a perspectiva da classe {@linkplain br.ps.timetable.model.Timetable}.
	 * 
	 * @see br.ps.timetable.model.Timetable
	 */
	public interface Timetable extends Lookup {}
}
