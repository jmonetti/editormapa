package comandosMenu;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import vista.Fondo;
import controlador.ControladorFondoModoDibujo;


public class ComandoAgregarRegion extends ComandoMenu {

	public void ejecutar() {
		JOptionPane.showMessageDialog(null,"Va a generar una región, ingrese todos los puntos y luego pulse terminar");
	
		Fondo.getInstance().addMouseListener(ControladorFondoModoDibujo.getInstance());
		
		//VistaMenu.getInstance().desHabilitarTodosHijos("Edicion");
	}
		
		
	
	

}
