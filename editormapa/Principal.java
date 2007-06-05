import modelo.Mapa;

import vista.VistaPrincipal;


public class Principal {


	private Mapa mapa;
	
	public static void main(String[] args) {
		
		Mapa mapa = Mapa.getInstance();
		//mapa.addObserver(Fondo.getInstance().getVistaMapa());
		VistaPrincipal vista = new VistaPrincipal();
		

	}

}
