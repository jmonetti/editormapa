package controlador;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import vista.VistaPrincipal;

public class ControladorFocoAreaDibujo implements FocusListener {

	public void focusGained(FocusEvent arg0) {
		VistaPrincipal.getInstance().getVistaMapa().repaint();
		System.out.println("Ganó el puto foco");

	}

	public void focusLost(FocusEvent arg0) {
		VistaPrincipal.getInstance().getVistaMapa().repaint();
		System.out.println("Perdio la concha de la lora");
	}

}
