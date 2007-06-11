package vista;

import java.awt.Polygon;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

public class VistaRegion extends Polygon{
	
	private int id;
	
	public VistaRegion(int[] px, int[] py, int length, int id) {
		super(px,py,length);
		this.id = id;
	}
	public int getId() {
		return this.id;
		
	}
	public Node toXml(Document dom) {
		
		Element vistaRegion = dom.createElement("Poligono");
		
		for(int indice = 0; indice < super.npoints; indice++){
			Element punto = dom.createElement("Punto");
			
			Element puntoX = dom.createElement("x");
			Text hijoPuntoX = dom.createTextNode(Integer.toString(super.xpoints[indice]));
			puntoX.appendChild(hijoPuntoX);
			
			Element puntoY = dom.createElement("y");
			Text hijoPuntoY = dom.createTextNode(Integer.toString(super.ypoints[indice]));
			puntoY.appendChild(hijoPuntoY);
			
			punto.appendChild(puntoX);
			punto.appendChild(puntoY);
			
			vistaRegion.appendChild(punto);
		}
		return vistaRegion;
	}
}
