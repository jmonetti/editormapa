package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;


import controlador.ControladorFocoVentanaPrincipal;
import controlador.ControladorVentanaPrincipal;


public class VistaPrincipal {

	public static JFrame marcoPrincipal=null;
	private VistaMapa mapa;
	private FrameRegion frameRegion;
	private JDesktopPane dp;
	private static VistaPrincipal instance= null;
	private VistaMenu menu;
	private FrameAgregarRegion frameAgregarRegion;
	
	public static VistaPrincipal getInstance(){
		if(instance == null)
			return crearInstancia();
		else
			return instance;
	}
	private static VistaPrincipal crearInstancia() {
		instance = new VistaPrincipal();
		return instance;
	}
	private VistaPrincipal(){
		startGUI();
	}
	public VistaMapa getVistaMapa(){
		return this.mapa;
	}
	public JDesktopPane getJDesktopPane(){
		return this.dp;
	}
	public FrameRegion getFrameRegion(){
		return this.frameRegion;
	}
	public FrameAgregarRegion getFrameAgregarRegion(){
		return frameAgregarRegion;
	}
	private void startGUI() {
		//creo el marco principal
		marcoPrincipal = new JFrame("Editor de mapas");
		//lo maximiso
		marcoPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//le agrego sus controladores
		marcoPrincipal.addWindowListener(new ControladorVentanaPrincipal());
		//marcoPrincipal.addWindowFocusListener(new ControladorFocoVentanaPrincipal());
		//creo la barra de menu
		menu = new VistaMenu();
		//agrego la barra al mnarco principal
		marcoPrincipal.setJMenuBar(menu);
		//agrego el panel del mapa
		mapa = new VistaMapa();
		//muestro el marco
		dp = new JDesktopPane();
		Dimension tamañoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
		dp.setSize(tamañoPantalla);
		dp.setBackground(Color.black);
		dp.setOpaque(true);
		frameRegion = new FrameRegion("Datos Región");
		dp.add(mapa);
		dp.add(frameRegion);
		dp.setVisible(true);
		frameAgregarRegion = new FrameAgregarRegion("Agregar región");
		frameAgregarRegion.setVisible(false);
		dp.add(frameAgregarRegion);

		marcoPrincipal.add(dp);
		marcoPrincipal.setVisible(true);
	}
	public VistaMenu getVistaMenu() {
		return menu;
	}
	

}
