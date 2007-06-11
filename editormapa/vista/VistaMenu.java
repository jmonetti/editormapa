package vista;

import java.awt.Component;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.MenuElement;

import controlador.ControladorMenu;

public class VistaMenu  extends JMenuBar{
	
	private void crearMenuArchivo(JMenuBar barra) {
		//Creo el menu Archivo
		JMenu menuArchivo = new JMenu("Archivo");
		menuArchivo.setName("Archivo");
		//Creo los sub menus
		JMenuItem menuAbrirImagen = new JMenuItem("Abrir Imagen..");
		JMenuItem menuGenerarArchivosJuego = new JMenuItem("Generar Archivos juego");
		JMenuItem menuSalir = new JMenuItem("Salir");
		//agrego los controladores
		menuAbrirImagen.addActionListener(ControladorMenu.getInstance());
		menuGenerarArchivosJuego.addActionListener(ControladorMenu.getInstance());
		menuSalir.addActionListener(ControladorMenu.getInstance());
		//los agrego al menu principal
		menuArchivo.add(menuAbrirImagen);
		menuArchivo.add(menuGenerarArchivosJuego);
		menuArchivo.addSeparator();
		menuArchivo.add(menuSalir);
		//lo agrego a la barra
		barra.add(menuArchivo);
		
		
	}
	private void crearMenuEdicion(JMenuBar barra){
		//Creo el menu Archivo
		JMenu menuEdicion = new JMenu("Edición");
		menuEdicion.setName("Edicion");
		menuEdicion.setEnabled(false);
		//Creo los sub menus
		JMenuItem menuAgregarRegion = new JMenuItem("Agregar región");
		JMenuItem menuQuitarRegion = new JMenuItem("Quitar región");
		menuQuitarRegion.addActionListener(ControladorMenu.getInstance());
		//los agrego al menu principal
		menuEdicion.add(menuAgregarRegion);
		menuEdicion.addSeparator();
		menuEdicion.add(menuQuitarRegion);
		
		//lo agrego a la barra
		barra.add(menuEdicion);
		
		menuAgregarRegion.addActionListener(ControladorMenu.getInstance());
		
	}
	public void desHabilitarMenu(String nombre){
		cambiarEstadoHabilitado(buscarMenu(nombre),false);
	}
	public void habilitarMenu(String nombre){
		cambiarEstadoHabilitado(buscarMenu(nombre),true);
		
	}
	private void cambiarEstadoHabilitado(Component c, boolean habilitado){
		if(c!=null)
			c.setEnabled(habilitado);
	}
	private Component buscarMenu(String nombre){
				
		Component[] subMenus = this.getComponents();
		int indice=0;
		boolean encontrado=false;
		while(encontrado==false && indice < subMenus.length){
			if(subMenus[indice].getName().equals(nombre))
				encontrado = true;
			else
				indice++;
		}
		if(encontrado)
			return subMenus[indice];
		else
			return null;
	}
	public VistaMenu(){
		//creo la barra de menus
		super();
		//creo todos los menus
		crearMenuArchivo(this);
		crearMenuEdicion(this);
	}
}
