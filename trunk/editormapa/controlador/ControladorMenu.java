package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import comandosMenu.ComandoAbrirImagen;
import comandosMenu.ComandoAgregarRegion;
import comandosMenu.ComandoMenu;
import comandosMenu.ComandoQuitarRegion;
import comandosMenu.ComandoSalir;
import comandosMenu.ComandoTerminar;

public class ControladorMenu implements ActionListener {
	
	private HashMap comandos;
	private static ControladorMenu controlador=null;
	
	private static ControladorMenu crearInstancia(){
		controlador = new ControladorMenu();
		return controlador;
	}
	public static ControladorMenu getInstance(){
		if(controlador == null)
			return crearInstancia();
		else 
			return controlador;
	}
	private ControladorMenu(){
		comandos = new HashMap();
		comandos.put("Abrir Imagen..", new ComandoAbrirImagen());
		comandos.put("Salir", new ComandoSalir());
		
		comandos.put("Agregar región" , new ComandoAgregarRegion());
		comandos.put("Terminar", new ComandoTerminar());
		comandos.put("Quitar región" , new ComandoQuitarRegion());
		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		ComandoMenu comandoAux = (ComandoMenu)comandos.get(arg0.getActionCommand()); 
		comandoAux.ejecutar();
		
	}
	
}
