package vista;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import controlador.ControladorMouseRegion;

import modelo.Mapa;



public class Fondo extends JRootPane implements Observer{
	
	private static Fondo instance= null;
	private ArrayList puntosX;
	private ArrayList puntosY;
	private JLabel labelImagen;
	private ArrayList Poligonos;
	
	public static Fondo getInstance(){
		if(instance == null)
			return crearInstancia();
		else
			return instance;
	}
	private static Fondo crearInstancia() {
		instance = new Fondo();
		return instance;
	}
	private Fondo(){
		//super();
		//creo una etiqueta para agregar la imagen de fondo
		labelImagen = new JLabel(); 
		//Creo la imagen de fondo
		ImageIcon imagen = new ImageIcon("mapa.gif");
		//agrego la imagen al fondo
		labelImagen.setIcon(imagen);
		labelImagen.setBackground(Color.blue);
		labelImagen.setOpaque(true);
		labelImagen.setBounds(0, 0, imagen.getIconWidth(), imagen.getIconHeight());
		labelImagen.setAlignmentX(SwingConstants.LEFT);
		labelImagen.setAlignmentY(SwingConstants.TOP);
		//this.getContentPane().add(labelImagen);
		this.getLayeredPane().add(labelImagen);
		this.getLayeredPane().add(new PanelRegion("Región"));
		//creo los contenedores de los puntos y de los poligonos			
		puntosX = new ArrayList();
		puntosY = new ArrayList();
		this.Poligonos = new ArrayList();
		
	}
	public boolean agregarPuntoAlArea(int x, int y){
		//si el punto pertenece a la imagen
		if( (x <= labelImagen.getWidth()) && (y <= labelImagen.getHeight()) ) {
			puntosX.add(new Integer(x));
			puntosY.add(new Integer(y));
			Graphics g = instance.getGraphics();
			//grafico un punto
			g.setColor(Color.red);
			g.fillOval(x,y, 8, 8);
			g.setColor(Color.black);
			return true;
		}
		else
			return false;
	}
	private void agregarRegion(int id){
		if(puntosX.size() >= 3){//si no hay mas de 3 no hago nada, porque son pocos puntos
			//Convierto los puntos a un array
			Object[] auxX = puntosX.toArray();
			Object[] auxY = puntosY.toArray();
			//borro los puntos (ya que correspondian a la region)
			puntosX.clear();
			puntosY.clear();
			//paso los vectores a int
			int[] pX = this.toInt(auxX);
			int[] pY = this.toInt(auxY);
			
			//creo un poligono
			Polygon p = new Polygon(pX,pY,pX.length);
			//creo la vista region
			VistaRegion regNueva =new VistaRegion(id,p);
			//lo agrego a los poligonos
			this.Poligonos.add(regNueva);
			//lo agrego a los contenidos de este objeto
			this.getContentPane().add(regNueva);
		}
		else
			JOptionPane.showMessageDialog(null,"Debe ingresar mas de 3 puntos para formar una región");
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
	public void dibujarPoligonos() {
		if(Poligonos!=null){
			for(int i=0;i<Poligonos.size();i++)
				((Component)Poligonos.get(i)).paint(this.getGraphics());
		}
	}
	public void dibujarPuntos() {
		Graphics g = this.getGraphics();
		if(puntosX != null){
			for(int i=0;i<puntosX.size();i++){
				int x = ((Integer)puntosX.get(i)).intValue();
				int y = ((Integer)puntosY.get(i)).intValue();
				g.fillOval(x,y, 8, 8);
			}
		}
	}
	public void paint(Graphics g){
		super.paint(g);
		dibujarPuntos();
		dibujarPoligonos();
	}
	public void repaint(){
		this.paint(this.getGraphics());
	}
	public void update(Observable o, Object arg) {
		Mapa m = (Mapa)o;		
		this.agregarRegion(0);
	}
}
