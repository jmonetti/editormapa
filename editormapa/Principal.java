import modelo.Mapa;
import vista.Fondo;
import vista.VistaPrincipal;


public class Principal {


	private Mapa mapa;
	
	public static void main(String[] args) {
		
		Mapa mapa = Mapa.getInstance();
		mapa.addObserver(Fondo.getInstance());
		VistaPrincipal vista = new VistaPrincipal();
		

	}

}
