package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.plaf.metal.MetalBorders;


import controlador.ControladorNoMover;
import controlador.ControladorSeleccion;

import modelo.Mapa;
import modelo.Region;
import modelo.eRegionNoExistente;

public class FrameRegion extends JInternalFrame implements ActualizablePorSeleccion, Observer{
	
		private JLayeredPane panel;
		private JLabel[] etiquetas;
		private JList listaLimitrofes;
		private DefaultListModel modeloListaLimitrofes;
		private JScrollPane scroller;
		
		//Modelo
		private Region region;
		
		private static int CantidadEtiquetas = 3;
		
		/**
		 * Inicializa la vista de la region
		 * @param titulo Titulo de la ventana de la vista region
		 */
		public FrameRegion(String titulo, int slot,int x,int y){
			//Llamo al constructor del padre
			super(titulo,false,false,false,false);
			this.setResizable(false); //Hago que no pueda cambiar su tamaño

			//Creo el panel
			panel = new JLayeredPane();
			//Defino el tamaño de la pantalla
			Dimension tamanoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
			//Establezco el tamaño y las coordenadas
			this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		    this.setBounds(x, y, tamanoPantalla.width / 3, tamanoPantalla.height / 3 - tamanoPantalla.height / 10);
			//Creo las etiquetas del panel y las agrego
			this.crearEtiquetas();
			this.PonerEtiquetasEnBlanco();
			//Creo el cuadro de limitrofes
			this.crearLista();
			//agrego el panel al frame
			this.getContentPane().add(panel);
			//agrego esta vista como observadora del modelo
			Mapa.getInstance().addObserver(this);
			//Hago a la ventana no movible
			this.addComponentListener(new ControladorNoMover(this.getX(), this.getY()));
			//agrego esta vista como actualizable por seleccion
			if (slot == 1)
				ControladorSeleccion.GetInstance().agregarVistaActualizableSlot1(this);
			else
				ControladorSeleccion.GetInstance().agregarVistaActualizableSlot2(this);
			this.setVisible(true);
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
			this.etiquetas[1].setText(" Dinero : ");
			this.etiquetas[2].setText(" Limitrofes : ");
		}
		
		/**
		 * Crea todo lo necesario para mostrar la lista de limitrofes
		 */
		private void crearLista(){
			Dimension tamañoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
			this.modeloListaLimitrofes = new DefaultListModel();
			this.listaLimitrofes = new JList(modeloListaLimitrofes);
			this.scroller = new JScrollPane (listaLimitrofes);
			Border borde = new MetalBorders.Flush3DBorder();
			scroller.setBorder(borde);
			scroller.setBackground(Color.white);
			scroller.setBounds(tamañoPantalla.width / 30, 80, tamañoPantalla.width / 4, tamañoPantalla.height / 12);
			panel.add(scroller);
		}
		
		private void cargarLimitrofes(DefaultListModel modeloLista, Region region){
				try {
					List limitrofes = Mapa.getInstance().getLimitrofes(region);
					for (int cont = 0; cont < limitrofes.size(); cont++){
						modeloLista.addElement(limitrofes.get(cont));
					}
				} catch (eRegionNoExistente e) {}
		}
		
		public void setNuevaRegion(Region region) {
			if (this.region != null)
				this.region.deleteObserver(this);
			this.region = region;
			modeloListaLimitrofes.removeAllElements();
			if (this.region != null){
				this.region.addObserver(this);
				cargarLimitrofes(modeloListaLimitrofes, this.region);
			} else
				PonerEtiquetasEnBlanco();
		}

		public void update(Observable arg0, Object arg1) {
			if (this.region != null){
				this.etiquetas[0].setText(" Region: " + this.region.getNombre());
				this.etiquetas[1].setText(" Dinero : " + this.region.getDinero());
				if (arg0 instanceof Mapa){
					modeloListaLimitrofes.removeAllElements();
					cargarLimitrofes(modeloListaLimitrofes, this.region);
				}
			} else
				PonerEtiquetasEnBlanco();
		}
	
}
