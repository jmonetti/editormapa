package comandosMenu;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import modelo.Mapa;


import vista.FrameAgregarRegion;
import vista.VistaMapa;
import vista.VistaMenu;
import vista.VistaPrincipal;
import controlador.ControladorFondoModoDibujo;
import controlador.ControladorMouseRegion;


public class ComandoAgregarRegion extends ComandoMenu {

	public void ejecutar() {
		JOptionPane.showMessageDialog(null,"Va a generar una región, ingrese todos los puntos y luego pulse terminar");
		VistaPrincipal.getInstance().getVistaMapa().getLabelFondo().removeMouseListener(ControladorMouseRegion.getInstance());		
		VistaPrincipal.getInstance().getVistaMapa().getLabelFondo().addMouseListener(ControladorFondoModoDibujo.getInstance());
		VistaPrincipal.getInstance().getVistaMenu().desHabilitarMenu("Edicion");
		VistaPrincipal.getInstance().getFrameAgregarRegion().setVisible(true);
	}
		
		
	
	

}
