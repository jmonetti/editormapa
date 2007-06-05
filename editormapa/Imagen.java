import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

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
