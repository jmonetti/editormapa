package vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToolBar;

import controlador.ControladorFocoVentanaPrincipal;
import controlador.ControladorMenu;
import controlador.ControladorVentanaPrincipal;


public class VistaPrincipal {

	public static JFrame marcoPrincipal=null;
	private Fondo fondo;
	
	public VistaPrincipal(){
		startGUI();
	}
	
	private void startGUI() {
		//creo el marco principal
		marcoPrincipal = new JFrame("Editor de mapas");
		//lo maximiso
		marcoPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//le agrego sus controladores
		marcoPrincipal.addWindowListener(new ControladorVentanaPrincipal());
		marcoPrincipal.addWindowFocusListener(new ControladorFocoVentanaPrincipal());
		//creo la barra de menu
		VistaMenu menu = VistaMenu.getInstance();
		//agrego la barra al mnarco principal
		marcoPrincipal.setJMenuBar(menu);
		//agrego el panel del mapa
		fondo = Fondo.getInstance();
		//muestro el marco
		marcoPrincipal.add(fondo);
		marcoPrincipal.setVisible(true);
		
	}
	
		// TODO Auto-generated method stub
		
	

}
