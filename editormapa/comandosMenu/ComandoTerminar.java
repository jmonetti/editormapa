package comandosMenu;

import modelo.Mapa;
import modelo.Region;
import controlador.ControladorFondoModoDibujo;

import vista.Fondo;

public class ComandoTerminar extends ComandoMenu {

	public void ejecutar() {
		Fondo.getInstance().removeMouseListener(ControladorFondoModoDibujo.getInstance());
		//Fondo.getInstance().addMouseListener(ControladorFondoModoSeleccion.getInstance());
		Region regionNueva = new Region("",0);
		Mapa.getInstance().AgregarRegion(regionNueva);
	}
	
}
