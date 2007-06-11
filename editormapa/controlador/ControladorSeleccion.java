package controlador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import modelo.Region;

import vista.ActualizablePorSeleccion;

/**
 * @author Grupo 2, Algoritmos y programacion 3, Catedra Fontela<br>1er cuat 2007    
 * <br>Esta clase se encarga de controlar la seleccion de regiones.
 * Esta implementada con el patron singleton.
 */

public class ControladorSeleccion {
	
	private Region slot1; //Region en el slot 1
	private Region slot2; //Region en el slot 2
	private List vistasActualizablesSlot1; 		//Listas de vistas actualizables por cambio de seleccion slot1
	private List vistasActualizablesSlot2; 	//Listas de vistas actualizables por cambio de seleccion slot2
	private static ControladorSeleccion Instance = null; //Instancia del controlador
	
	/**
	 * Inicializa el controlador
	 */
	private ControladorSeleccion(){
		slot1 = null;
		slot2 = null;
		vistasActualizablesSlot1 = new ArrayList();
		Instance = this;
		vistasActualizablesSlot2 = new ArrayList();
	}
	
	/**
	 * @return La instancia del controlador. 
	 * Si no existe la crea y la devuelve.
	 */
	public static ControladorSeleccion GetInstance(){
		if (Instance == null)
			return CrearInstancia();
		else
			return Instance;
	}
	
	/**
	 * Crea una nueva instancia del controlador
	 * @return la nueva instancia
	 */
	private static ControladorSeleccion CrearInstancia(){
		Instance = new ControladorSeleccion();
		return Instance;
	}
	
	/**
	 * Setea una nueva region en el slot 1.
	 * @param region Region a setear.
	 */
	public void setSlot1 (Region region){
		if((slot2 != region) && (slot1 != region)){
				slot1 = region;
				actualizarVistasSlot1();
				region.ActualizarObservadores();
		}
	}
	/**
	 * Libera los dos slots
	 */
	public void limpiarSeleccion(){
		slot1 = null;
		slot2 = null;
		actualizarVistasSlot1();
		actualizarVistasSlot2();
	}
	/**
	 * Setea una nueva region en el slot 2.
	 * @param region Region a setear.
	 */
	public void setSlot2 (Region region){
		//no permito seleccionar en los dos slots la misma region
		if((slot2 != region) && (slot1 != region)){
				slot2 = region;
				actualizarVistasSlot2();
				region.ActualizarObservadores();
			}
	}
	/**
	 * @return Devuelve la region seleccionada en el slot 1
	 */
	public Region getSlot1 (){
		return this.slot1;
	}
	
	/**
	 * @return Devuelve la region seleccionada en el slot 2
	 */
	public Region getSlot2 (){
		return this.slot2;
	}
	
	/**
	 * Agrega una nueva vista para actualizarse cuando cambie la seleccion en el slot 1
	 * @param vista Vista a agregar
	 */
	public void agregarVistaActualizableSlot1 (ActualizablePorSeleccion vista){
		if (vista != null)
			this.vistasActualizablesSlot1.add(vista);
	}
	
	/**
	 * Agrega una nueva vista para actualizarse cuando cambie la seleccion en el slot 2
	 * @param vista Vista a agregar
	 */
	public void agregarVistaActualizableSlot2 (ActualizablePorSeleccion vista){
		if (vista != null)
			this.vistasActualizablesSlot2.add(vista);
	}
	
	/**
	 * Actualiza las vistas agregadas en la lista de actualizacion para slot 1
	 */
	private void actualizarVistasSlot1(){
		Iterator iteradorLista = vistasActualizablesSlot1.iterator();
		while (iteradorLista.hasNext()){
			ActualizablePorSeleccion vista = (ActualizablePorSeleccion)iteradorLista.next();
			vista.setNuevaRegion(slot1);
		}
	}
	
	/**
	 * Actualiza las vistas agregadas en la lista de actualizacion para slot 2
	 */
	private void actualizarVistasSlot2(){
		Iterator iteradorLista = vistasActualizablesSlot2.iterator();
		while (iteradorLista.hasNext()){
			ActualizablePorSeleccion vista = (ActualizablePorSeleccion)iteradorLista.next();
			vista.setNuevaRegion(slot2);
		}
	}
}
