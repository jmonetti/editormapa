package vista;

import java.awt.Component;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.MenuElement;

import controlador.ControladorMenu;

public class VistaMenu  extends JMenuBar{
	private static VistaMenu instance= null;
	
	public static VistaMenu getInstance(){
		if(instance == null)
			return crearInstancia();
		else
			return instance;
	}
	private static VistaMenu crearInstancia() {
		instance = new VistaMenu();
		return instance;
	}
	private void crearMenuArchivo(JMenuBar barra) {
		//Creo el menu Archivo
		JMenu menuArchivo = new JMenu("Archivo");
		menuArchivo.setName("Archivo");
		//Creo los sub menus
		JMenuItem menuAbrirImagen = new JMenuItem("Abrir Imagen..");
		JMenuItem menuSalir = new JMenuItem("Salir");
		menuSalir.addActionListener(ControladorMenu.getInstance());
		//los agrego al menu principal
		menuArchivo.add(menuAbrirImagen);
		menuArchivo.add(menuSalir);
		//lo agrego a la barra
		barra.add(menuArchivo);
		
		menuAbrirImagen.addActionListener(ControladorMenu.getInstance());
	}
	private void crearMenuEdicion(JMenuBar barra){
		//Creo el menu Archivo
		JMenu menuEdicion = new JMenu("Edición");
		menuEdicion.setName("Edicion");
		//Creo los sub menus
		JMenuItem menuAgregarRegion = new JMenuItem("Agregar región");
		JMenuItem menuEdicionTerminar = new JMenuItem("Terminar");
		menuEdicionTerminar.setEnabled(true);
		JMenuItem menuQuitarRegion = new JMenuItem("Quitar región");
		menuQuitarRegion.addActionListener(ControladorMenu.getInstance());
		//los agrego al menu principal
		menuEdicion.add(menuAgregarRegion);
		menuEdicion.add(menuEdicionTerminar);
		menuEdicion.addSeparator();
		menuEdicion.add(menuQuitarRegion);
		
		//lo agrego a la barra
		barra.add(menuEdicion);
		
		menuAgregarRegion.addActionListener(ControladorMenu.getInstance());
		menuEdicionTerminar.addActionListener(ControladorMenu.getInstance());
	}
	//TODO TERMINAR ESTO!!!
	/*
	public void desHabilitarTodosHijos(String nombre){
		VistaMenu menu = VistaMenu.getInstance();
		Component[] subMenus = menu.getComponents();
		int indice=0;
		boolean encontrado=false;
		while(encontrado==false && indice < subMenus.length){
			if(subMenus[indice].getName().equals(nombre))
				encontrado = true;
			else
				indice++;
		}
		if(encontrado){
			//JMenuItem[] componentes = ((JMenu)subMenus[indice]).getme
			for(int i = 0;i< componentes.length;i++)
				componentes[i].setEnabled(false);
		}
	}*/
	private VistaMenu(){
		//creo la barra de menus
		super();
		//creo todos los menus
		crearMenuArchivo(this);
		crearMenuEdicion(this);
	}
}
