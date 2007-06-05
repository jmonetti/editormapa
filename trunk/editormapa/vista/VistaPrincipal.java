package vista;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import controlador.ControladorFocoVentanaPrincipal;
import controlador.ControladorMenu;
import controlador.ControladorNoMover;
import controlador.ControladorVentanaPrincipal;


public class VistaPrincipal {

	public static JFrame marcoPrincipal=null;
	private VistaMapa mapa;
	private PanelRegion panelRegion;
	
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
		mapa = mapa.getInstance();
		//muestro el marco
		marcoPrincipal.getLayeredPane().setVisible(true);
		marcoPrincipal.getLayeredPane().add(mapa);
		marcoPrincipal.setVisible(true);
				
		panelRegion = new PanelRegion("Datos Regiòn");
		//marcoPrincipal.getContentPane().add(panelRegion);
	}
	
		// TODO Auto-generated method stub
		
	

}
