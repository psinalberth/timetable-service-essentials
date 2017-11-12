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
	 * Define o nível de visão primário, ou seja, de atributos que pertencem ao próprio dominio explorado.
	 */
	public interface Foreign {}
	
	
	public interface Public {}
	
	/**
	 * Utilizada quando o objeto representado possui outros objetos associados.
	 */
	public interface Compound extends Public {}
}
