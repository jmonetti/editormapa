package modelo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Observable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

import modelo.grafo.*;

/**@author Grupo 2, Algoritmos y programación 3, Cátedra Fontela<br>1er cuat 2007
 * <br>Esta clase representa el mapa
 */
public class Mapa extends Observable{
	
	private Grafo regiones;//grafo con las regiones
	private String nombre;
	private String ruta;
	private static Mapa instance;
	
	private static Mapa crearInstancia(){
		instance = new Mapa();
		return instance;
	}
	public static Mapa getInstance(){
		if(instance!=null)
			return instance;
		else
			return crearInstancia();
	}
	
	private Mapa(){
		this.regiones = new Grafo();
		this.nombre ="";
		this.ruta= "";
	}
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	public String getNombre(){
		return nombre;
	}
	public void setRuta(String ruta){
		this.ruta = ruta;
	}
	public String getRuta(){
		return ruta;
	}
	
	/**
	 * Agrega una región nueva al mapa 
	 * @param regionNueva Región a agregar
	 */
	public void AgregarRegion(Region regionNueva){
		try{
			regiones.agregarVertice(regionNueva);
		}catch(Exception e){}
	}
	/**
	 * Busca una region en el mapa
	 * @param o Objeto contra el cual se va a comparar. Por ejemplo Integer.
	 * @param c Comparador para realizar la busqueda
	 * @return La region si la encontro, de lo contrario null
	 */
	public Region buscarRegion(Object o,Comparator c){
		Vertice vertice =this.regiones.buscarVertice(o, c);
		if (vertice != null)
			return (Region)vertice.getInfo();
		else
			return null;
	}
	/**
	 * Permite quitar una region del mapa. La busca por el id de la region
	 * @param region Region que se desea quitar
	 */
	public void QuitarRegion(Region region){
		try{
			Vertice vertice = regiones.buscarVertice(region,new ComparadorRegionesId());
			regiones.eliminarVertice(vertice);
		}catch(Exception e){}
	}
	/**
	 * Permite settear como limitrofes a dos regiones
	 * @param region1
	 * @param region2
	 * @throws eRegionNoExistente Si una de las regiones no existe
	 */
	public void setLimitrofes(Region region1, Region region2) throws eRegionNoExistente{
		try{
			Object infoArista = null;
			//las busca en el mapa
			Vertice vertice1 = regiones.buscarVertice(region1, new ComparadorRegionesId());
			Vertice vertice2 = regiones.buscarVertice(region2,new ComparadorRegionesId());
			
			if ( vertice1 == null || vertice2 == null)
				throw new eRegionNoExistente();
			else
				//si los encontro agrega una arista no dirigida entre ambas regiones
				regiones.agregarAristaNoDirigida(vertice1, vertice2, infoArista);
			ActualizarObservadores();
		}catch(NullPointerException e){}
	}
	/**
	 * Devuelve en un ArrayList todas las regiones limitrofes de la pasada por parametro
	 * @param region Region de la cual se quieren obtener las limitrofes
	 * @return Un arraylist con todas las limitrofes
	 * @throws eRegionNoExistente Si la region pasada por parametro no existe
	 */
	public ArrayList getLimitrofes(Region region) throws eRegionNoExistente{
		ArrayList limitrofes = new ArrayList();
		try{
			//busco el vertice
			Vertice vertice = regiones.buscarVertice(region, new ComparadorRegionesId());
			//obtengo las aristas salientes
			Iterator iteradorAristas = vertice.getAristasSalientes().iterator();
			Arista aristaAux;
			//recorro las aristas y obtengo todos los vertices a los que apuntan
			while(iteradorAristas.hasNext()){
				aristaAux = (Arista)iteradorAristas.next();
				limitrofes.add(aristaAux.getVerticeDestino().getInfo());
			}
			
		}catch(NullPointerException e){}
		return limitrofes;
	}
	/**
	 * Este método permite saber si dos regiones son limitrofes
	 * @param region1
	 * @param region2
	 * @return true si son limitrofes, false en caso contrario
	 */
	public boolean sonLimitrofes(Region region1, Region region2){
		boolean encontrado = false;
		try{
			ArrayList limitrofes = getLimitrofes(region1);
			int indice = 0;
			while( (indice< limitrofes.size()) && (!encontrado)){
				if( region2.equals((Region)limitrofes.get(indice)))
						encontrado = true;
				indice ++;
			}
		}catch(Exception e){}
		return encontrado;
	}
	/**
	 * Devuelve la cantidad de regiones del mapa
	 * @return int con la cantidad de regiones
	 */
	public int getCantidadRegiones(){
		return regiones.getCantidadVertices();
	}
	/**
	 * Devuelve en un arraylist todas las regiones
	 * @return arraylist con todas las regiones
	 */
	public ArrayList getRegiones(){
		return regiones.getInfoVertices().toArrayList();
		
	}
	public String toString(){
		String cadena = new String();
		
		cadena += "Cantidad de regiones: "+ getCantidadRegiones() + "\n";
		cadena += this.regiones.toString();
		return cadena;
	}
	/**
	 * Actualiza los observadores
	 */
	public void ActualizarObservadores(){
		this.setChanged();
		this.notifyObservers();
	}
	public Node limitrofesToXml(Document xmlDoc) {
		//creo el elemento de los limitrofes
		Element elementoLimitrofes = xmlDoc.createElement("ArchivoLimitrofes");
		//obtengo todas las regiones
		Iterator itRegiones = this.getRegiones().iterator();
		ArrayList limitrofes;
		Region reg1;
		Region reg2;
		
		while(itRegiones.hasNext()){
			//creo el elemento que coniene los dos limitrofes
			Element elementoRegiones = xmlDoc.createElement("limitrofes");
			//obtengo todas las regiones
			reg1 = (Region)itRegiones.next();
			
			//creo elemento para agregar la region
			Element elementoReg1 = xmlDoc.createElement("region1");
			Text hijoReg1 = xmlDoc.createTextNode(reg1.getNombre());
			elementoReg1.appendChild(hijoReg1);
			
			
			Iterator itLimitrofes;
			Element elementoReg2 = null;
			try {
				//obtengo todas las limitros de la region
				itLimitrofes = this.getLimitrofes(reg1).iterator();
				while(itLimitrofes.hasNext()){
					//obtengo cada limitrofes
					reg2 = (Region)itLimitrofes.next();
					//creo el elemento
					elementoReg2 = xmlDoc.createElement("region2");
					Text hijoReg2 = xmlDoc.createTextNode(reg2.getNombre());
					//lo agrego al nodo
					elementoReg2.appendChild(hijoReg2);
				}
			} catch (eRegionNoExistente e) {}//se que existe seguro
			//agrego los dos elementos de las regiones
			elementoRegiones.appendChild(elementoReg1);
			elementoRegiones.appendChild(elementoReg2);
			//agrego los elementos de regiones al de limitrofes
			elementoLimitrofes.appendChild(elementoRegiones);
		}
		//devuelvo el elemento generado con todos los limitrofes
		return elementoLimitrofes;
	}
	public Node regionesToXml(Document xmlDoc) {
		Element elementoRegiones = xmlDoc.createElement("regiones");
		Iterator it = this.getRegiones().iterator();
		while(it.hasNext())
			elementoRegiones.appendChild(((Region)it.next()).toXml(xmlDoc)); 
		return elementoRegiones;
	}
}