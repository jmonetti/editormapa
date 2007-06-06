package controlador;

import java.awt.Component;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

import sun.awt.RepaintArea;
import vista.VistaMapa;
import vista.VistaPrincipal;

public class ControladorFocoVentanaPrincipal implements WindowFocusListener {

	public void windowGainedFocus(WindowEvent e) {
		VistaPrincipal.getInstance().getVistaMapa().repaint();
		System.out.println("a");
		/*JFrame f = (JFrame)e.getSource();
		f.getComponent(0).repaint();*/
	}
	
	
	
	public void windowLostFocus(WindowEvent e) {
		VistaPrincipal.getInstance().getVistaMapa().repaint();
		System.out.println("a");
		// TODO Auto-generated method stub

	}

}
