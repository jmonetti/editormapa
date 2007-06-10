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

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import controlador.ControladorNoMover;

import modelo.Mapa;

public class VistaMapa extends JInternalFrame implements Observer{
	
	private JLabel labelImagen;
	private List puntosX;
	private List puntosY;
	private List Poligonos;
	public JLayeredPane panel;
	
	/**
	 * Constructor de la clase. Inicializa la vista del mapa.
	 *
	 */
	public VistaMapa(){
		super();
		panel = new JLayeredPane();
		//creo los arraylist para los puntos		
		puntosX = new ArrayList();
		puntosY = new ArrayList();
		//creo el array para los poligonos
		this.Poligonos = new ArrayList();
		//agrego el panel principal al frame
		this.getContentPane().add(panel);
		//lo pongo visible
		this.setVisible(true);
		//le agrego el controlador que no permite que se mueva de su lugar
		this.addComponentListener(new ControladorNoMover(this.getX(), this.getY()));
	}
	/**
	 * Agrega un punto nuevo al mapa. Un conjunto de estos permiten formar un poligono.
	 * @param x Coordenada x
	 * @param y
	 * @return
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
	private boolean agregarRegion(int id){
		if(puntosX.size() >= 3){//si no hay mas de 3 no hago nada, porque son pocos puntos
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
			return true;
		}
		else{
			JOptionPane.showMessageDialog(null,"Debe ingresar mas de 3 puntos para formar una región");
			return false;
		}
	}
	public void borrarPuntos(){
		this.puntosX.clear();
		this.puntosY.clear();
	}
	private int[] toInt(Object[] v) {
		int[] vectorAux = new int[v.length];
		for(int i=0;i<v.length;i++)
			vectorAux[i]=((Integer)v[i]).intValue();
		
		return vectorAux;
	}
	public void update(Observable o, Object arg) {
		Mapa m = (Mapa)o;		
		this.agregarRegion(0);
	}
	public void setImagen(String path) {
		
		//creo una etiqueta para agregar la imagen de fondo
		labelImagen = new LabelFondo(path, puntosX, puntosY, Poligonos); 
		panel.add(labelImagen);
		
		Dimension tamanoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(0, 0, labelImagen.getWidth(), 2*tamanoPantalla.height / 3);
	}
	public Component getLabelFondo() {
		return this.labelImagen;
	}
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
}
