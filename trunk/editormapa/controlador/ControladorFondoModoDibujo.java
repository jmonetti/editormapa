package controlador;

import java.awt.Canvas;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JRootPane;

import sun.security.jca.GetInstance;
import vista.Fondo;


public class ControladorFondoModoDibujo implements MouseListener{

	private static ControladorFondoModoDibujo instance;
	
	private ControladorFondoModoDibujo(){
		super();
	}
	private static ControladorFondoModoDibujo crearInstancia(){
		instance = new ControladorFondoModoDibujo();
		return (ControladorFondoModoDibujo)instance;
	}
	public static ControladorFondoModoDibujo getInstance(){
		if(instance==null)
			return crearInstancia();
		else
			return instance;
	}
	public void mouseClicked(MouseEvent arg0) {
		Fondo fondo = Fondo.getInstance();
		fondo.agregarPuntoAlArea(arg0.getX(), arg0.getY());
		
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
