package controlador;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import vista.VistaPrincipal;
import vista.VistaRegion;

public class ControladorMouseRegion implements MouseListener {

	private static ControladorMouseRegion instance=null; 
	
	private static ControladorMouseRegion crearInstancia(){
		instance = new ControladorMouseRegion();
		return instance;
	}
	public static ControladorMouseRegion getInstance(){
		if (instance==null){
			return crearInstancia();
		}
		else
			return instance;
	}
	private ControladorMouseRegion(){
		super();
	}
	public void mouseClicked(MouseEvent arg0) {
		Point punto = new Point(arg0.getX(), arg0.getY());
		VistaRegion vR = VistaPrincipal.getInstance().getVistaMapa().getPoligonoEn(punto);
		if(vR != null)
			System.out.println(vR.getBounds());
		else
			System.out.println("No clickeaste nada huevon!");
		
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
