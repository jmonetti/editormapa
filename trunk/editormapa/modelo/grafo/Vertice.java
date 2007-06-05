package modelo.grafo;

import modelo.grafo.*;


/**
 * @author   Grupo 2, Algoritmos y programación 3, Cátedra Fontela<br>1er cuat 2007  <br>Esta clase modela el vertice del grafo
 */
public class Vertice {
	/**
	 * @uml.property  name="info"
	 */
	private Object info;
	private Conjunto aristas;
	
	/**
	 * 
	 * @param contenido Contenido que posee el vertice a crearse. Si bien puede ser vacío(null),
	 * se recomienda que no lo sea ya que no permite la correcta identificacion de los mismos
	 */
	public Vertice(Object contenido){
		this.info = contenido;
		this.aristas = new Conjunto();
	}
	private void AgregarArista(Arista aristaNueva){
		try{
			this.aristas.agregarElemento(aristaNueva);
		}catch(Exception e){}
	}
	/**
	 * @return  La info contenida en el vertice
	 * @uml.property  name="info"
	 */
	public Object getInfo(){
		return this.info;
	}
	/**
	 * @param aristaNueva Arista saliente que se desea agregar al vertice
	 * @throws NullPointerException Un error producido por intentar agregar una arista null 
	 */
	public void agregarAristaSaliente(Arista aristaNueva) throws NullPointerException{
		if(aristaNueva == null)
			throw new NullPointerException();
		else
			this.aristas.agregarElemento(aristaNueva);
	}
	/**
	 * @param arista Arista a eliminar
	 * @throws NullPointerException Si la arista pasada como parámetro es null
	 */
	public void elimiarAristaSaliente(Arista arista) throws NullPointerException{
		if(arista != null)
			this.aristas.eliminarElemento(arista);
		else
			throw new NullPointerException();
	}
	/**
	 * @return ConjuntoAristas El conjunto de aristas que posee el vertice
	 * @see grafo.ConjuntoAritas
	 */
	public Conjunto getAristasSalientes(){
		return this.aristas;
	}
	
	public String toString(){
		return info.toString();
	}
	
}
