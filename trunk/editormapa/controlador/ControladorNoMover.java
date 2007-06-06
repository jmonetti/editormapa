package controlador;

import java.awt.AWTEvent;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JInternalFrame;

public class ControladorNoMover implements ComponentListener {

	private int posX;
	private int posY;
	
	public ControladorNoMover(int posX, int posY){
		this.posX = posX;
		this.posY = posY;
	}
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void componentMoved(ComponentEvent arg0) {

		JInternalFrame frame = (JInternalFrame)arg0.getSource();
		
		frame.setLocation(this.posX,this.posY);
		
		//System.out.println("entro");

	}

	public void componentResized(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

}
