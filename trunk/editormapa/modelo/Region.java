
package modelo;

import java.util.Observable;


/**
 * @author          Grupo 2, Algoritmos y programacin 3, Ctedra Fontela<br>1er cuat 2007    
 * <br>Esta clase representa la regin que puede ser incluida en el mapa.
 */
public class Region extends Observable{

	private static int idActual = 0;
	
	private int Id;
	private String nombre;
	private int dinero;
		
	private static int asignarIdActual(){
		Region.idActual++;
		return Region.idActual-1;
	}
	
	/**
	 * Constructor
	 * @param nombre Nombre que va a tener la region
	 * @param dinero Dinero que entrega la Regin por turno
	 */
	public Region(String nombre, int dinero){
		this.nombre = new String(nombre);
		this.setDinero(dinero);
		this.Id = Region.asignarIdActual();
	}
	/**
	 * Devuelve el id de la region
	 * @return int representando el valor del id de la region
	 */
	public int getId(){
		return this.Id;
	}
	/**
	 * @param dinero      Dinero que se desea asignar a la Region
	 */
	public void setDinero(int dinero){
		this.dinero = dinero;
	}
	/**
	 * @return      El dinero que entrega la Region por turno
	 */
	public int getDinero(){
		return this.dinero;
	}
	public String getNombre(){
		return this.nombre;
	}
	/**
	 * @param nombreNuevo      Nombre nuevo para la regin
	 */
	public void setNombre(String nombreNuevo){
		this.nombre= nombreNuevo;
	}
	/**
	 * @return      Todos los edificios que tiene la regin
	 */
		
	public boolean equals(Object o){
	
		if(o == null)
			return false;
		else{
			Region r2 = (Region)o;
			return (this.getId() == r2.getId());
		}
	}
}
