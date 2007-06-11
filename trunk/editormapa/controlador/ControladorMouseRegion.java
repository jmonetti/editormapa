package controlador;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import modelo.ComparadorIdRegionConInt;
import modelo.Mapa;
import modelo.Region;
import modelo.eRegionNoExistente;

import vista.VistaPrincipal;
import vista.VistaRegion;

public class ControladorMouseRegion implements MouseListener {

	private static ControladorMouseRegion instance=null; 
	
	private static ControladorMouseRegion crearInstancia(){
		instance = new ControladorMouseRegion();
		return instance;
	}
	public static ControladorMouseRegion getInstance(){
		if (instance==null){
			return crearInstancia();
		}
		else
			return instance;
	}
	private ControladorMouseRegion(){
		super();
	}
	public void mouseClicked(MouseEvent arg0) {
		Point punto = new Point(arg0.getX(), arg0.getY());
		VistaRegion vR = VistaPrincipal.getInstance().getVistaMapa().getPoligonoEn(punto);
		Region region = null;
		int id = -1;
		if (vR != null)
			id = vR.getId();
		region = Mapa.getInstance().buscarRegion(new Integer(id), new ComparadorIdRegionConInt());
		if (arg0.getButton() == MouseEvent.BUTTON1)
			ControladorSeleccion.GetInstance().setSlot1(region);
		else
			ControladorSeleccion.GetInstance().setSlot2(region);
		Region region1 = ControladorSeleccion.GetInstance().getSlot1();
		Region region2 = ControladorSeleccion.GetInstance().getSlot2();
		if ((region1 != null) && (region2 != null) &&
			(!Mapa.getInstance().sonLimitrofes(region1 , region2)))
				pedirLimitrofes(region1, region2);
	}
	
	private void pedirLimitrofes (Region  region1, Region region2){
		int respuesta = JOptionPane.showConfirmDialog(VistaPrincipal.getInstance().getVistaMapa(), "¿Desea hacer a las regiones limitrofes?", "Limitrofes", JOptionPane.YES_NO_OPTION);
		if (respuesta == JOptionPane.YES_OPTION)
			try {
				Mapa.getInstance().setLimitrofes(region1, region2);
			} catch (eRegionNoExistente e) {}
	}
	
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
