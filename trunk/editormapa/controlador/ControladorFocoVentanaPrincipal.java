package controlador;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import vista.VistaPrincipal;

public class ControladorFocoVentanaPrincipal implements WindowFocusListener {

	public void windowGainedFocus(WindowEvent e) {
		VistaPrincipal.getInstance().getVistaMapa().repaint();
	}
	
	
	
	public void windowLostFocus(WindowEvent e) {
		VistaPrincipal.getInstance().getVistaMapa().repaint();
	}

}
