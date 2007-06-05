package vista;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JButton;

import controlador.ControladorMouseRegion;

public class VistaRegion extends Component {
	
	private Polygon p;
	private int id;
	
	public VistaRegion(int id, Polygon p){
		this.p = p;
		this.id = id;
		this.setBounds(p.getBounds());
		this.addMouseListener(ControladorMouseRegion.getInstance());
	}
	public void paint(Graphics g){
		g.fillPolygon(p);
	}
	public Polygon getPolygon(){
		return this.p;
	}
	public int getId() {
		return this.id;
		
	}
}
