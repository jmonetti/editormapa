package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.plaf.metal.MetalBorders;
import javax.swing.plaf.metal.MetalInternalFrameUI;


import controlador.ControladorNoMover;

import modelo.Region;



public class FrameRegion extends JInternalFrame implements Observer{
	
		private JLayeredPane panel;
		private JLabel[] etiquetas;
		
		private static int CantidadEtiquetas = 2;
		
		/**
		 * Inicializa la vista de la region
		 * @param titulo Titulo de la ventana de la vista region
		 */
		public FrameRegion(String titulo){
			//Llamo al constructor del padre
			super(titulo,false,false,false,false);
			this.setResizable(false); //Hago que no pueda cambiar su tamaño

			//Creo el panel
			panel = new JLayeredPane();
			//Defino el tamaño de la pantalla
			Dimension tamanoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
			//Establezco el tamaño y las coordenadas
			panel.setBounds(tamanoPantalla.width/3, 2 * tamanoPantalla.height / 3, tamanoPantalla.width / 3, tamanoPantalla.height / 3 - 75);
		    this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		    this.setBounds(tamanoPantalla.width/3, 2 * tamanoPantalla.height / 3, tamanoPantalla.width / 3, tamanoPantalla.height / 3 - tamanoPantalla.height / 10);
		    this.setLocation(0,(int) (2*tamanoPantalla.getHeight()/3) );
			//Creo las etiquetas del panel y las agrego
			this.crearEtiquetas();
			this.PonerEtiquetasEnBlanco();
			//agrego el panel al frame
			this.getContentPane().add(panel);
			//agrego esta vista como observadora del modelo
			this.addComponentListener(new ControladorNoMover(this.getX(), this.getY()));
			this.setVisible(true);
			
		}
		
		/**
		 * Se encarga de actualizar a la vista, cuando el modelo cambia.
		 */
		public void update(Observable arg0, Object arg1) {
			//Seteo el texto de las etiquetas con los datos del modelo
			//this.etiquetas[0].setText(" Region: " + (modelo.getNombre())+" ");
			//this.etiquetas[2].setText(" Dinero que otorga : " + (modelo.getDinero())+" ");
			
			
		}
		/**
		 * Crea, configura y agrega las etiquetas al panel de la vista
		 */
		private void crearEtiquetas(){
			//Defino el tamaño de la pantalla
			Dimension tamañoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
			//Creo las etiquetas, las configuro y las agrego al panel
			this.etiquetas = new JLabel[CantidadEtiquetas];
			Border borde = new MetalBorders.Flush3DBorder();
			for (int cont = 0; cont < CantidadEtiquetas; cont++){
				this.etiquetas[cont] = new JLabel();
				this.etiquetas[cont].setBorder(borde);
				this.etiquetas[cont].setFont( new Font("Helvetica", Font.BOLD, 13 ) );
				this.etiquetas[cont].setBounds(10, 22 * cont + 10,tamañoPantalla.width / 3 - 20, 20);
				this.panel.add(etiquetas[cont]);
			}
			
		}
		/**
		 * Pone todas las etiquetas en blanco
		 */
		private void PonerEtiquetasEnBlanco(){
			this.etiquetas[0].setText(" Region: ");
			this.etiquetas[1].setText(" Civilización : ");
			
		}
	
}
