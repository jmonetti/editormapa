package modelo.grafo;


import modelo.grafo.*;
import java.util.*;
/**
 * @author          Grupo 2, Algoritmos y programación 3, Cátedra Fontela<br>1er cuat 2007  <br>Esta clase tiene el mismo sentido que un grafo matematico. Se pueden agregar, quitar, buscar  vertices, asi como tambien crear relaciones dirigidas o no dirigidas entre los mismos
 * @see  grafo.Vertice
 */
public class Grafo {
	/**
	 */
	private Conjunto vertices;
	
	public Grafo(){
		this.vertices = new Conjunto();
	}
	/**
	 * @return       El conjunto de vertices que contiene el grafo.
	 * @see  grafo.Vertice
	 */
	public Conjunto getVertices(){
		return this.vertices;
	}
	/**
	 * @return Un conjunto de elementos con la info de los vertices
	 */
	public Conjunto getInfoVertices(){
		Conjunto conjunto = new Conjunto();
		Iterator it = getVertices().iterator();
		while(it.hasNext()){
			Vertice verticeAux = (Vertice)it.next();
			conjunto.agregarElemento(verticeAux.getInfo());
		}
		return conjunto;
	}
	/**
	 * @return La cantidad de vertices que tiene el grafo 
	 */
	public int getCantidadVertices(){
		return getVertices().getCantidadElementos();
	}
	/**
	 * 
	 * @return True si el grafo no contiene ningun vertice y false si contiene por lo menos
	 * uno (no importa si tiene o no aristas)
	 */
	public boolean estaVacio(){
		if(vertices.getCantidadElementos() == 0)
			return true;
		else
			return false;
	}
	/**
	 * 
	 * @param info Contenido que va a tener el vertice a agregar
	 * @throws NullPointerException Si la informacion es null, devuelve un objeto de error
	 * @see errores.NullPointerException
	 */
	public void agregarVertice(Object info) throws NullPointerException{
		if(info != null){
			Vertice nuevoVertice = new Vertice(info);
			this.vertices.agregarElemento(nuevoVertice);
		}
		else
			throw new NullPointerException();
	}
	/**
	 * 
	 * @param vertice Vertice que se desea eliminar
	 * @throws NullPointerException Error producido al pasar como parametro un objeto nulo.
	 * @see errores.NullPointerException
	 */
	public void eliminarVertice(Vertice vertice) throws NullPointerException{
		if( vertice == null)
			throw new NullPointerException();
		else{
			Iterator iteradorVertices = vertices.iterator();
			Arista aristaAux;
			Vertice verticeAux;
			//obtengo todos los vertices del grafo
			while (iteradorVertices.hasNext()){
				verticeAux = (Vertice)iteradorVertices.next();
				//obtengo sus aristas salientes
				Iterator iteradorAristas = verticeAux.getAristasSalientes().iterator();
				//mientras tenga aristas
				while(iteradorAristas.hasNext()){
					//obtengo la arista
					aristaAux = (Arista)iteradorAristas.next();
					//si el vertice destino es el que se quiere eliminar elimino la arista 
					//para que no quede apuntando a algo que ya no esta en el grafo
					if(aristaAux.getVerticeDestino() == vertice)
						verticeAux.elimiarAristaSaliente(aristaAux);
				}
			}
			//elimino el vertice
			this.vertices.eliminarElemento(vertice);
		}
	}
	/**
	 * 
	 * @param verticeOrigen Vertice que va a ser origen en la relación
	 * @param verticeDestino Vertice de destino
	 * @param infoArista Contenido de la arista (puede ser nulo, pero 
	 * tener en cuenta eso a la hora de trabajar con esto
	 * @throws NullPointerException Si alguno de los vertices es null, tira este error
	 * @see errores.NullPointerException
	 */
	public void agregarAristaDirigida(Vertice verticeOrigen, Vertice verticeDestino, Object infoArista) throws NullPointerException{
		if( (verticeOrigen ==null) || (verticeDestino ==null) )
			throw new NullPointerException();
		else{
			Arista arista = new Arista(infoArista,verticeDestino);
			verticeOrigen.agregarAristaSaliente(arista);
		}
	}
	/**
	 * <br><p>Nota: Tener en cuenta que no agrega realmente una arista no dirigida, sino que agrega
	 * dos aristas dirigidas (una a cada vertice) lo que conceptualmente es lo mismo.<br><br> 
	 * @param vertice1 Uno de los dos vertices de la relación
	 * @param vertice2 Segundo vertice
	 * @param infoArista Contenido de la arista que los une
	 * @throws NullPointerException Error producido si alguno de los vertices es null
	 * @see errores.NullPointerException
	 */
	public void agregarAristaNoDirigida(Vertice vertice1, Vertice vertice2, Object infoArista) throws NullPointerException{
		
		if( (vertice1 ==null) || (vertice1 ==null) )
			throw new NullPointerException();
		else{
			Arista arista = new Arista(infoArista,vertice2);
			vertice1.agregarAristaSaliente(arista);
			arista = new Arista(infoArista, vertice1);
			vertice2.agregarAristaSaliente(arista);
		}
	}
	/**
	 * 
	 * @param info Contenido del vertice que se desea buscar
	 * @return Vertice Devuelve el vertice si es que fue encontrado, de lo contrario, devuelve null. Si
	 * habia dos Vertices con el mismo contenido (no recomentable) devuelve solo el primero. Para comparar
	 * utiliza el Object.equals(Object 0)
	 * @see Object
	 * @throws NullPointerException Si se pasa un contenido nulo
	 * @see errores.NullPointerException
	 */
	public Vertice buscarVertice(Object info, Comparator c) throws NullPointerException{
		
		if(info == null)
			throw new NullPointerException();
		else{
			boolean encontrado = false;
			Iterator iterador = vertices.iterator();
			Vertice verticeAux= null;
					
			while( iterador.hasNext() && !encontrado ){
				verticeAux = (Vertice)iterador.next();
				if(c.compare(verticeAux.getInfo(),info) == 0)
					encontrado = true;
				
			}
			if(encontrado)
				return verticeAux;
			else
				return null;
		}
	}
	
	public String toString(){
		Iterator it = vertices.iterator();
		String cadena = new String();
		while(it.hasNext()){
			cadena += it.next().toString() + "\n"; 
		}
		return cadena;
	}
	
}
