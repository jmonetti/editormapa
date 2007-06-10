package vista;

import java.awt.Polygon;

public class VistaRegion extends Polygon{
	
	private int id;
	
	public VistaRegion(int[] px, int[] py, int length, int id) {
		super(px,py,length);
		this.id = id;
	}
	public int getId() {
		return this.id;
		
	}
}
