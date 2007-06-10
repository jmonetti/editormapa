package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.VistaPrincipal;

public class ControladoBotonCancelar implements ActionListener{

	public void actionPerformed(ActionEvent arg0) {
		VistaPrincipal vp =VistaPrincipal.getInstance(); 
		vp.getVistaMapa().removeMouseListener(ControladorFondoModoDibujo.getInstance());
		vp.getVistaMenu().habilitarMenu("Edicion");
		vp.getVistaMapa().borrarPuntos();
		VistaPrincipal.getInstance().getFrameAgregarRegion().setVisible(false);
	}

}
