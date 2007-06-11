package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import controlador.ControladorFondoModoDibujo;
import controlador.ControladorMouseRegion;

public class LabelFondo  extends JLabel {

	private List puntosX;
	private List puntosY;
	private List Poligonos;
	private String path;
	
	public LabelFondo(String path, List puntosX, List puntosY, List Poligonos){
		super();
		//Creo la imagen de fondo
		ImageIcon imagen = new ImageIcon(path);
		//agrego la imagen al fondo
		this.setIcon(imagen);
		this.setBackground(Color.blue);
		this.setBounds(0, 0, imagen.getIconWidth(), imagen.getIconHeight());
		this.puntosX = puntosX;
		this.puntosY = puntosY;
		this.Poligonos = Poligonos;
		//this.addMouseListener(ControladorMouseRegion.getInstance());
		this.path = path;
	}
	
	public void paint(Graphics g){
		super.paint(g);
		for(int i=0;i<puntosX.size();i++){
			int x = ((Integer)puntosX.get(i)).intValue();
			int y = ((Integer)puntosY.get(i)).intValue();
			g.setColor(Color.red);
			g.fillOval(x,y, 9, 9);
		}
		if(Poligonos!=null){
			for(int i=0;i<Poligonos.size();i++){
				Polygon p = (Polygon)Poligonos.get(i);
				g.fillPolygon(p);

			}
		}
	}
	public String getPath(){
		return this.path;
	}
}
