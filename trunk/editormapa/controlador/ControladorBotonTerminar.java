package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import comandosMenu.ComandoMenu;

import modelo.Mapa;
import modelo.Region;


import vista.VistaMapa;
import vista.VistaPrincipal;

public class ControladorBotonTerminar implements ActionListener {

	public void actionPerformed(ActionEvent arg0) {
		VistaPrincipal vp =VistaPrincipal.getInstance(); 
		
		VistaPrincipal.getInstance().getVistaMapa().getLabelFondo().removeMouseListener(ControladorFondoModoDibujo.getInstance());
		VistaPrincipal.getInstance().getVistaMapa().getLabelFondo().addMouseListener(ControladorMouseRegion.getInstance());
		
		vp.getVistaMenu().habilitarMenu("Edicion");
		Region regionNueva = new Region("",0);
		Mapa.getInstance().AgregarRegion(regionNueva);
		VistaPrincipal.getInstance().getFrameAgregarRegion().setVisible(false);
		VistaPrincipal.getInstance().getVistaMapa().borrarPuntos();
	}
	
}
