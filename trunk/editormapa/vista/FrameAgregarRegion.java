package vista;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.plaf.metal.MetalBorders;

import controlador.ControladorBotonActualizar;
import controlador.ControladorFocusearSiempre;

public class FrameAgregarRegion extends JFrame {
	
	//Componentes
	private JLayeredPane panel;
	private JTextField[] datos;
	private JLabel[] etiquetas;
	private JButton actualizar;
	
	private static int CantidadDatos = 2;
	
	public FrameAgregarRegion(String titulo){
		//Llamo al constructor del padre
		super(titulo);
		this.setResizable(false); //Hago que no pueda cambiar su tamaño

		//Creo el panel
		panel = new JLayeredPane();
		//Defino el tamaño de la pantalla
		Dimension tamanoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
		//Establezco el tamaño y las coordenadas
	    this.setBounds(tamanoPantalla.width / 3, tamanoPantalla.height / 3, tamanoPantalla.width / 3, tamanoPantalla.height / 3);
	    
		//Creo las etiquetas del panel y las agrego
		this.crearEtiquetas();
		
		//Creo las cajas de texto
		this.crearCajasDeTexto();
		
		//Creo los botones
	    this.agregarBotones(panel);
		//agrego el panel al frame
		this.getContentPane().add(panel);
		
		//Hago la ventana siempre focuseada
		this.addWindowFocusListener(new ControladorFocusearSiempre());
		this.setAlwaysOnTop(true);
		//Hago esta vista visible
		this.setVisible(true);
	}

	/**
	 * Crea los botones y los agrega al panel
	 * @param panel
	 */
	private void agregarBotones(JLayeredPane panel) {
		Dimension tamanoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
		actualizar = new JButton("Actualizar Datos");
		actualizar.setBounds(tamanoPantalla.width / 10, tamanoPantalla.height / 4, 160, 30);
		panel.add(actualizar);
		actualizar.addActionListener(new ControladorBotonActualizar(this));
	}
	
	/**
	 * Crea, configura y agrega las etiquetas al panel de la vista
	 */
	private void crearEtiquetas(){
		//Defino el tamaño de la pantalla
		Dimension tamañoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
		//Creo las etiquetas, las configuro y las agrego al panel
		this.etiquetas = new JLabel[CantidadDatos];
		Border borde = new MetalBorders.Flush3DBorder();
		for (int cont = 0; cont < CantidadDatos; cont++){
			this.etiquetas[cont] = new JLabel();
			this.etiquetas[cont].setBorder(borde);
			this.etiquetas[cont].setFont( new Font("Helvetica", Font.BOLD, 13 ) );
			this.etiquetas[cont].setBounds(10, 44 * cont + 5,tamañoPantalla.width / 3 - 20, 20);
			this.panel.add(etiquetas[cont]);
		}
		this.etiquetas[0].setText(" Region: ");
		this.etiquetas[1].setText(" Dinero : ");
	}
	
	/**
	 * Crea, configura y agrega las cajas de texto al panel de la vista
	 */
	private void crearCajasDeTexto(){
		//Defino el tamaño de la pantalla
		Dimension tamañoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
		//Creo las etiquetas, las configuro y las agrego al panel
		this.datos = new JTextField[CantidadDatos];
		Border borde = new MetalBorders.Flush3DBorder();
		for (int cont = 0; cont < CantidadDatos; cont++){
			this.datos[cont] = new JTextField();
			this.datos[cont].setBorder(borde);
			this.datos[cont].setFont( new Font("Helvetica", Font.BOLD, 13 ) );
			this.datos[cont].setBounds(10, 44 * cont + 25,tamañoPantalla.width / 3 - 20, 20);
			this.panel.add(datos[cont]);
		}
	}
	
	/**
	 * @return El nombre de la region, ingresado en el campo region
	 */
	public String getNombreRegion(){
			return this.datos[0].getText();
	}
	
	/**
	 * @return El dinero de la region, ingresado en el campo dinero
	 */
	public int getDineroRegion(){
		int Dinero = 0;
		try{
			Dinero = Integer.parseInt(this.datos[1].getText());
		} catch (Exception e){
			JOptionPane.showMessageDialog(this, "Dinero mal ingresado");
			return Dinero;
		}
		return Dinero;
	}
}

