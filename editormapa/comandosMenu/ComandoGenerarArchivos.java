package comandosMenu;

import javax.swing.JOptionPane;

import modelo.Mapa;
import vista.VistaPrincipal;

public class ComandoGenerarArchivos extends ComandoMenu {

	public void ejecutar() {
		//si la cantidad de regiones es mayor a 0 genero el archivo de configuracion
		if(Mapa.getInstance().getCantidadRegiones() > 0){
			
			
		}
		else
			JOptionPane.showMessageDialog(VistaPrincipal.getInstance().getJDesktopPane(),"Debe ingresar al menos una región para poder generar el archivo de configuración");
	}

}
