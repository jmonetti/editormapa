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
	private FrameRegion frameRegion1;
	private FrameRegion frameRegion2;
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
		return this.frameRegion1;
	}
	public FrameAgregarRegion getFrameAgregarRegion(){
		return frameAgregarRegion;
	}
	private void startGUI() {
		//Defino el tamaño de la pantalla
		Dimension tamanoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
		//creo el marco principal
		marcoPrincipal = new JFrame("Editor de mapas");
		//lo maximiso
		marcoPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//le agrego sus controladores
		marcoPrincipal.addWindowListener(new ControladorVentanaPrincipal());
		//creo la barra de menu
		menu = new VistaMenu();
		//agrego la barra al mnarco principal
		marcoPrincipal.setJMenuBar(menu);
		//creo el panel del mapa
		mapa = new VistaMapa();
		
		//Creo el desktop pane y le agrego las vistas principales
		dp = new JDesktopPane();
		dp.setSize(tamanoPantalla);
		dp.setBackground(Color.gray);
		dp.setOpaque(true);
		frameRegion1 = new FrameRegion("Datos Región Slot 1", 1, 0,(int) (2*tamanoPantalla.getHeight()/3));
		frameRegion2 = new FrameRegion("Datos Región Slot 2", 2, tamanoPantalla.width / 3,(int) (2*tamanoPantalla.getHeight()/3));
		dp.add(mapa);
		dp.add(frameRegion1);
		dp.add(frameRegion2);

		//	muestro el marco
		marcoPrincipal.add(dp);
		marcoPrincipal.setVisible(true);
		
		frameAgregarRegion = new FrameAgregarRegion("Agregar región");
		frameAgregarRegion.setVisible(false);
	}
	public VistaMenu getVistaMenu() {
		return menu;
	}
	

}
