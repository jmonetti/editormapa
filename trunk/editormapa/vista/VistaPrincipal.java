package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import controlador.ControladorFocoVentanaPrincipal;
import controlador.ControladorFondoModoDibujo;
import controlador.ControladorMenu;
import controlador.ControladorNoMover;
import controlador.ControladorVentanaPrincipal;


public class VistaPrincipal {

	public static JFrame marcoPrincipal=null;
	private VistaMapa mapa;
	private FrameRegion panelRegion;
	
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
		//mapa = mapa.getInstance();
		mapa = VistaMapa.getInstance();
		//muestro el marco
		JDesktopPane dp = new JDesktopPane();
		Dimension tamañoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
		dp.setSize(tamañoPantalla);
		dp.setBackground(Color.black);
		dp.setOpaque(true);
		
		panelRegion = new FrameRegion("Datos Región");
		dp.add(mapa);
		dp.add(panelRegion);
		dp.setVisible(true);
		

		marcoPrincipal.add(dp);
		marcoPrincipal.setVisible(true);
	}
	

}
