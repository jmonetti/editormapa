package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.Mapa;
import modelo.Region;

import vista.FrameAgregarRegion;
import vista.VistaMapa;
import vista.VistaPrincipal;

public class ControladorBotonActualizar implements ActionListener {

	private FrameAgregarRegion vista; //Vista que controla
	
	public ControladorBotonActualizar (FrameAgregarRegion vista){
		this.vista = vista;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		//obtengo las vistas
		VistaPrincipal vistaPrincipal = VistaPrincipal.getInstance(); 
		VistaMapa vistaMapa = vistaPrincipal.getVistaMapa();  
		//Si la cantidad de puntos es menor a 3 no permito que se ingrese
		if(vistaMapa.getCantidadPuntos() >= 3){
			//saco el escuchador de dibujo
			vistaMapa.getLabelFondo().removeMouseListener(ControladorFondoModoDibujo.getInstance());
			//agrego el escuchador de la seleccion
			vistaMapa.getLabelFondo().addMouseListener(ControladorMouseRegion.getInstance());
			//habilito el menú edicion
			vistaPrincipal.getVistaMenu().habilitarMenu("Edicion");
			//creo una nueva region vacia
			Region regionNueva = new Region(vista.getNombreRegion(),vista.getDineroRegion());
			//Agrego a la vista mapa como observadora de la region y actualizo los obs
			regionNueva.addObserver(vistaMapa);
			regionNueva.ActualizarObservadores();
			//la agrego al mapa
			Mapa.getInstance().AgregarRegion(regionNueva);
			//escondo la ventana de agregar
			FrameAgregarRegion.getInstance().setVisible(false);
			//borro los puntos del mapa
			vistaMapa.borrarPuntos();
		}
		else
			JOptionPane.showMessageDialog(VistaPrincipal.getInstance().getJDesktopPane(),"Debe ingresar mas de 3 puntos para formar una región");
	}
	
}
