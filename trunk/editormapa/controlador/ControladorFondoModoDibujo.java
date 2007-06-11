package controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import vista.FrameAgregarRegion;
import vista.VistaMapa;
import vista.VistaPrincipal;


public class ControladorFondoModoDibujo implements MouseListener{

	private static ControladorFondoModoDibujo instance;
	
	private ControladorFondoModoDibujo(){
		super();
	}
	private static ControladorFondoModoDibujo crearInstancia(){
		instance = new ControladorFondoModoDibujo();
		return (ControladorFondoModoDibujo)instance;
	}
	public static ControladorFondoModoDibujo getInstance(){
		if(instance==null)
			return crearInstancia();
		else
			return instance;
	}
	public void mousePressed(MouseEvent arg0) {
		VistaMapa mapa = VistaPrincipal.getInstance().getVistaMapa();
		if (arg0.getButton() == MouseEvent.BUTTON1)
			mapa.agregarPunto(arg0.getX(), arg0.getY());
		if (arg0.getButton() == MouseEvent.BUTTON3){
			FrameAgregarRegion.getInstance().setVisible(true);
			FrameAgregarRegion.getInstance().limpiarCajasDeTexto();
			FrameAgregarRegion.getInstance().requestFocus();
		}
	}
	public void mouseClicked(MouseEvent arg0) {	}

	public void mouseEntered(MouseEvent arg0) {}

	public void mouseExited(MouseEvent arg0) {}

	public void mouseReleased(MouseEvent arg0) {}
}
