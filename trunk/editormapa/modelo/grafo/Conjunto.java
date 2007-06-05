package modelo.grafo;
import java.util.*;

/**
 * @author Grupo 2, Algoritmos y programación 3, Cátedra Fontela<br>1er cuat 2007
 * <br>Esta clase modela un conjunto de elementos. Estos no pueden estar repetidos y
 * no tienen ningun orden en particular. Asi que para recorrerlos leer sobre Iterator.
 * @see Iterator
 *
 */
public class Conjunto {
	private List elementos;
	
	public Conjunto(){
		this.elementos = new ArrayList();
	}
	/**
	 * @return La cantidad de elementos que posee el conjunto
	 */
	public int getCantidadElementos(){
		return (elementos.size());
	}
	/**Permite obtener un iterador para recorrer los elementos
	 * @return Un elemento que implemente la case Iterator, lo que permitira poder recorrerlo
	 * @see Iterator
	 */
	public Iterator iterator(){
		return new IteradorConjunto(this);
	}
	/**
	 * Agrega un elemento nuevo al conjunto. No puede estar repetido.
	 * @param elementoNuevo Elemento que se desea agregar. Si el elemento ya existia no lo agrega.
	 * Para chequear esto, usa el object.Equals(Object arg0), asi que si no esta redefinido, compara por
	 * su referencia, asi que es recomendable hacerlo.
	 * @throws NullPointerException Si el objeto a eliminar era nulo tira esta excepcion
	 * @see errores.NullPointerException
	 */
	public void agregarElemento(Object elementoNuevo) throws NullPointerException{
		if(elementoNuevo == null)
			throw new NullPointerException();
		else
			if(!existeElemento(elementoNuevo))//chequeo que el elemento no exista
				elementos.add(elementoNuevo);
	}
	/**
	 * Busca un elemento dentro del conjunto
	 * @param o Objeto al cual se va a buscar o comparar
	 * @param c Comparador que se usa para la busqueda
	 * @return El objeto si lo encontro, null en caso contrario
	 * @see Comparator
	 */
	public Object buscarElemento(Object o, Comparator c){
		Iterator it = iterator();
		boolean encontrado = false;
		Object oActual=null;
		//recorro el iterador hasta encontrar el elemento o hasta que no queden mas
		while(it.hasNext() && encontrado == false){
			oActual = it.next();
			if(c.compare(oActual, o)==0)//uso el comparador que me pasaron para buscarlo
				encontrado = true;
		}
		if(encontrado)
			return oActual;//si lo encontre lo devuelvo
		else
			return null;//si no devuelvo null
		
	}
	/**
	 * Este método permite saber si existe el elemento
	 * @param elemento Elemento que se desea saber si existe
	 * @return True si el elemento existe y false si no. Para comparar utiliza el método
	 * object.Equals(Object arg0).
	 */
	public boolean existeElemento(Object elemento){
		return (elementos.lastIndexOf(elemento) != -1);
	}
	/**
	 * @return True si esta vacio (cantidad elementos 0), false en caso contrario
	 */
	public boolean estaVacio(){
		return (elementos.isEmpty());
	}
	/**
	 * @param elemento Elemento que se desea eliminar. Compara mediante el object.Equals(Object arg0)
	 * @throws NullPointerException Si el objeto a eliminar es nulo, tira una excepcion del tipo EObjetoNulo
	 * @see errores.NullPointerException
	 */
	public void eliminarElemento( Object elemento)throws NullPointerException{
		if( elemento != null)
			elementos.remove(elemento);
		else
			throw new NullPointerException();
	}
	/**
	 * Quita todos los elementos del conjunto dejandolo vacio
	 *
	 */
	public void vaciar(){
		this.elementos.retainAll(elementos);
	}
	/**
	 * @return Devuelve todos los elementos en un ArrayList, sin ningun orden en particular
	 */
	public ArrayList toArrayList(){
		ArrayList aAux = (ArrayList)elementos;
		return (ArrayList) (aAux.clone());
	}
	/**
	 * @author   Grupo 2, Algoritmos y programación 3, Cátedra Fontela<br>1er cuat 2007
	 *  <br>Esta clase implementa un tipo de iterador para el conjunto. 
	 * @see  Iterator
	 */
	private class IteradorConjunto implements Iterator {

		private List conjunto;
		private int indiceActual;
		/**
		 * @param conjuntoAristas Conjunto de aristas sobre el cual se va a crear el iterador
		 */
		public IteradorConjunto(Conjunto conjunto){
			this.conjunto = conjunto.toArrayList();//guardo los elementos en forma de List
			this.indiceActual = 0;
		}
		/**
		 * @return boolean True si el iterador tiene un elemento más y false en caso contrario
		 */
		public boolean hasNext() {
			if(conjunto.size() > indiceActual)
				return true;
			else
				return false;
		}
		/**
		 * @return Object El objeto actual del iterador. Tambien, avanza una posicion en la iteración.
		 * Si no hay objeto siguiente, devuelve null
		 */
		public Object next() {
			if( this.hasNext() ){
				indiceActual++;
				return conjunto.get(indiceActual-1);
			}
			else
				return null;
		}
		public void remove() {
			
		}

	}
}
