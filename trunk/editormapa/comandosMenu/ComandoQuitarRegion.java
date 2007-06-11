package comandosMenu;

import javax.swing.JOptionPane;

import vista.VistaMapa;
import vista.VistaPrincipal;
import modelo.Mapa;
import modelo.Region;
import controlador.ControladorSeleccion;

public class ComandoQuitarRegion extends ComandoMenu {

	public void ejecutar() {
		Region region = ControladorSeleccion.GetInstance().getSlot1();
		VistaMapa VM = VistaPrincipal.getInstance().getVistaMapa();
		if (region != null){
			VM.borrarPoligono(region.getId());
			Mapa.getInstance().QuitarRegion(region);
			JOptionPane.showMessageDialog(VM, "Region Eliminada");
			ControladorSeleccion.GetInstance().setSlot1(null);
		} else
			JOptionPane.showMessageDialog(VM, "Ninguna region seleccionada para borrar", "Eliminacion", JOptionPane.ERROR_MESSAGE);
		VM.getLabelFondo().repaint();
	}

}
