package comandosMenu;

import javax.swing.JOptionPane;
import vista.VistaPrincipal;
import controlador.ControladorFondoModoDibujo;
import controlador.ControladorMouseRegion;


public class ComandoAgregarRegion extends ComandoMenu {

	public void ejecutar() {
		JOptionPane.showMessageDialog(null,"Va a generar una región, ingrese todos los puntos y luego pulse terminar");
		VistaPrincipal.getInstance().getVistaMapa().getLabelFondo().removeMouseListener(ControladorMouseRegion.getInstance());		
		VistaPrincipal.getInstance().getVistaMapa().getLabelFondo().addMouseListener(ControladorFondoModoDibujo.getInstance());
		VistaPrincipal.getInstance().getVistaMenu().desHabilitarMenu("Edicion");
	}
		
}
