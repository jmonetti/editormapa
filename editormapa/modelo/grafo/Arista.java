package modelo.grafo;

/**
 * @author          Grupo 2, Algoritmos y programaci�n 3, C�tedra Fontela<br>1er cuat 2007  <br>Esta clase modela la arista del grafo
 */
public class Arista{
	
	/**
	 */
	private Object info;
	private Vertice verticeDestino;
	
	/**@param info  Informaci�n que va a contener la arista
	 * @param verticeDestino  Vertice al cual conduce la arista
	 * 
	 */
	public Arista(Object info, Vertice verticeDestino){
		this.info = info;
		this.setVerticeDestino(verticeDestino);
	}
	/**
	 * @return       La informaci�n contenida en la arista
	 */
	public Object getInfo(){
		return this.info;
	}
	/**
	 * @return       Vertice al cual apunta la arista
	 */
	public Vertice getVerticeDestino(){
		return this.verticeDestino;
	}
	/**
	 * @param vertice       Vertice que se desea que sea el destino de la arista
	 */
	public void setVerticeDestino(Vertice vertice){
		this.verticeDestino = vertice;
	}
}
