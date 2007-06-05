package controlador;

import java.awt.Component;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

import sun.awt.RepaintArea;
import vista.Fondo;

public class ControladorFocoVentanaPrincipal implements WindowFocusListener {

	public void windowGainedFocus(WindowEvent e) {
		Fondo.getInstance().repaint();
		System.out.println("FOCUSEO!");
		/*JFrame f = (JFrame)e.getSource();
		f.getComponent(0).repaint();*/
	}
	
	
	
	public void windowLostFocus(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
