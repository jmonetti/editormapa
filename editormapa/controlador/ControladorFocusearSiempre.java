package controlador;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

public class ControladorFocusearSiempre implements WindowFocusListener {

	public void windowGainedFocus(WindowEvent arg0) {
	
	}

	public void windowLostFocus(WindowEvent arg0) {
		JFrame ventana = (JFrame)arg0.getSource();
		ventana.requestFocus();
	}

}
