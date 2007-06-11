package vista;

import modelo.Region;

/**
 * @author Grupo 2, Algoritmos y programacion 3, Catedra Fontela<br>1er cuat 2007    
 * <br> Esta interfaz se encarga de representar a aquellas vistas que pueden ser actualizadas
 * por un cambio en la selección de una region.
 */
public interface ActualizablePorSeleccion {
	/**
	 * Setea una nueva region a la vista.
	 * @param region Nueva region a agregar.
	 */
	public void setNuevaRegion(Region region);
	
}
