package vista;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import controlador.ControladorNoMover;

import modelo.ComparadorIdRegionConInt;
import modelo.Mapa;
import modelo.Region;

public class VistaMapa extends JInternalFrame implements Observer{
	
	private JLabel labelImagen;
	private List puntosX;
	private List puntosY;
	private List Poligonos;
	public JScrollPane panel;
	
	/**
	 * Constructor de la clase. Inicializa la vista del mapa.
	 *
	 */
	public VistaMapa(){
		super();
		//creo los arraylist para los puntos		
		puntosX = new ArrayList();
		puntosY = new ArrayList();
		//creo el array para los poligonos
		this.Poligonos = new ArrayList();
		//lo pongo visible
		this.setVisible(true);
		//le agrego el controlador que no permite que se mueva de su lugar
		this.addComponentListener(new ControladorNoMover(this.getX(), this.getY()));
		
	}
	/**
	 * Agrega un punto nuevo al mapa. Un conjunto de estos permiten formar un poligono. Si el punto se encuentra fuera
	 * de la imagen del mapa no lo agrega.
	 * @param x Coordenada x del punto a agregar
	 * @param y Coordenada y del punto a agregar
	 * @return True si pudo agregar el punto, false en caso contrario.
	 */
	public boolean agregarPunto(int x, int y){
		//si el punto pertenece a la imagen
		if( (x <= labelImagen.getWidth()) && (y <= labelImagen.getHeight()) ) {
			puntosX.add(new Integer(x));
			puntosY.add(new Integer(y));
			labelImagen.repaint();
			return true;
		}
		else
			return false;
	}
	/**
	 * Permite agregar una vista region al mapa. Para esto, usa los puntos que este contiene.
	 * @param id Id de la region que se representa con la vista
	 * 
	 */
	private void agregarRegion(int id){
		//Convierto los puntos a un array
		Object[] auxX = puntosX.toArray();
		Object[] auxY = puntosY.toArray();
		
		//paso los vectores a int
		int[] pX = this.toInt(auxX);
		int[] pY = this.toInt(auxY);
		
		//creo un poligono
		Polygon regNueva = new VistaRegion(pX,pY,pX.length, id);
		//lo agrego a los poligonos
		this.Poligonos.add(regNueva);
		labelImagen.repaint();
	}
	/**
	 * Devuelve la cantidad de puntos que hay insertados
	 * @return
	 */
	public int getCantidadPuntos(){
		return puntosX.size();
	}
	/**
	 * Borra los puntos
	 */
	public void borrarPuntos(){
		this.puntosX.clear();
		this.puntosY.clear();
	}
	/**
	 * Borra los poligonos cargados
	 */
	public void borrarPoligonos() {
		this.Poligonos.clear();
	}
	private int[] toInt(Object[] v) {
		int[] vectorAux = new int[v.length];
		for(int i=0;i<v.length;i++)
			vectorAux[i]=((Integer)v[i]).intValue();
		
		return vectorAux;
	}
	/**
	 * Establece que realizar cuando el modelo cambia
	 */
	public void update(Observable o, Object arg) {
		Region region = (Region) o;
		agregarRegion(region.getId());
	}
	/**
	 * Cambia la imagen de fondo
	 * @param path Ruta donde se encuentra la imagen de fondo
	 */
	public void setImagen(String path) {
		//borro lo anterior
		borrarPuntos();
		borrarPoligonos();
		Mapa.getInstance().vaciar();
		this.getContentPane().removeAll();
		//creo una etiqueta para poner el fondo
		labelImagen = new LabelFondo(path, puntosX, puntosY, Poligonos); 
		panel = new JScrollPane(labelImagen);
		this.getContentPane().add(panel);
		Dimension tamanoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(0, 0, tamanoPantalla.width, 2*tamanoPantalla.height / 3);
	}
	/**
	 * @return La instancia de imagen de fondo
	 */
	public Component getLabelFondo() {
		return this.labelImagen;
	}
	/**
	 * Busca la vista region que contenga a un punto determinado
	 * @param p Punto donde se desea encontrar un poligono
	 * @return La vista region encontrada, de lo contrario devuelve null.
	 */
	public VistaRegion getPoligonoEn(Point p){
		int i = 0;
		boolean encontrado = false;
		while(i<Poligonos.size() && !encontrado){
			if( ((Polygon)Poligonos.get(i)).contains(p) == true)
				encontrado = true;
			else
				i++;
		}
		if(encontrado)
			return (VistaRegion)Poligonos.get(i);
		else
			return null;
	}
	/**
	 * Busca una vista region que tenga el id pasado por parametro y la elimina
	 * @param id Id que se esta buscando
	 * @return true si la pudo borrar, false en caso contrario
	 */
	public boolean borrarPoligono(int id){
		int i = 0;
		boolean encontrado = false;
		while(i<Poligonos.size() && !encontrado){
			if( ((VistaRegion)Poligonos.get(i)).getId() == id)
				encontrado = true;
			else
				i++;
		}
		if(encontrado){
			Poligonos.remove(i);
			return true;
		} else
			return false;
	}
	
	public Element toXml(Document dom){
		Element vista = dom.createElement("Vista");
		
		Element src = dom.createElement("Src");
		Text hijoSrc = dom.createTextNode(((LabelFondo)this.labelImagen).getPath());
		src.appendChild(hijoSrc);
		
		
		for(int indice = 0; indice < Poligonos.size(); indice++){
			VistaRegion vistaRegion = (VistaRegion)this.Poligonos.get(indice);
			Region reg = Mapa.getInstance().buscarRegion(new Integer(vistaRegion.getId()), new ComparadorIdRegionConInt());
			if(reg != null){
				Element region = dom.createElement("Region");
				
				Element nombre = dom.createElement("Nombre");
				Text hijoNombre = dom.createTextNode(reg.getNombre());
				
				region.appendChild(nombre);
				region.appendChild(vistaRegion.toXml(dom));
				
				vista.appendChild(region);
			}
			
			
		}
		return vista;
	}
}
