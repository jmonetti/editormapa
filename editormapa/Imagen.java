import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Polygon;

public class Imagen extends Canvas{

	private Polygon p;
	
	public Imagen(){
		super();
		p = new Polygon();
		
	}
	public void agregarPunto(int X, int Y){
		p.addPoint(X, Y);
		
	}
	public void paint( Graphics g ){
		g.drawPolygon(p);
	}
}
