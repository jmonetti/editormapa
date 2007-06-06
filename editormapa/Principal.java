import modelo.Mapa;

import vista.VistaMapa;
import vista.VistaPrincipal;


public class Principal {


	private Mapa mapa;
	
	public static void main(String[] args) {
		
		Mapa mapa = Mapa.getInstance();
		
		mapa.addObserver(VistaPrincipal.getInstance().getVistaMapa());

	}

}
