import modelo.Mapa;
import vista.VistaPrincipal;


public class Principal {

	public static void main(String[] args) {
		
		Mapa.getInstance();
		VistaPrincipal.getInstance().getVistaMapa();
		

	}

}
