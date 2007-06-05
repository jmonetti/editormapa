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
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.plaf.metal.MetalBorders;
import javax.swing.plaf.metal.MetalInternalFrameUI;

import controlador.ControladorNoMover;

import modelo.Region;



public class PanelRegion extends JInternalFrame implements Observer{
	
	//Componentes
	private JPanel panel;
	private JLabel[] etiquetas;
	
	//Modelo
	private Region modelo;
	
	public PanelRegion(String titulo){
		//Llamo al constructor del padre
		super(titulo,false,false,false,false);
		this.setResizable(false);
		//Agrego esta vista como actualizable en el controlador de seleccion
		//ControladorSeleccion.GetInstance().agregarVistaActualizable(this);
		//Conecto con el modelo
		
		//Defino el tamaño de la pantalla
		Dimension tamanoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
		//Establezco el tamaño y las coordenadas
		this.setBounds(tamanoPantalla.width/3, 2 * tamanoPantalla.height / 3, new Integer(tamanoPantalla.width / 3).intValue(), tamanoPantalla.height / 3 - 75);
	    this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		//Creo el panel
		panel = new JPanel();
		panel.setBounds(tamanoPantalla.width/3, 2 * tamanoPantalla.height / 3, tamanoPantalla.width / 3, tamanoPantalla.height / 3 - 75);
		this.setLocation(0, (int)(tamanoPantalla.getWidth() - panel.getWidth())-100);
		//Creo las etiquetas del panel y las agrego
		this.crearEtiquetas();
		this.PonerEtiquetasEnBlanco();
		//agrego el panel al frame
		this.getContentPane().add(panel);
		//agrego esta vista como observadora del modelo
		if (modelo != null)
			modelo.addObserver(this);
		this.addComponentListener(new ControladorNoMover(this.getX(), this.getY()));
		this.setVisible(true);
	}
	
	
	public void update(Observable arg0, Object arg1) {
		if (modelo!=null){
			this.etiquetas[0].setText(" Region: " + (modelo.getNombre())+" ");
			this.etiquetas[2].setText(" Dinero que otorga : " + (modelo.getDinero())+" ");

		} else
			PonerEtiquetasEnBlanco();
	}

	public void setNuevaRegion(Region region){
		if (modelo!=null)
			modelo.deleteObserver(this);
		this.modelo = region;
		if (modelo!=null)
			modelo.addObserver(this);
	}
	
	private void crearEtiquetas(){
		this.etiquetas = new JLabel[2];
		Border borde = new MetalBorders.Flush3DBorder();
		for (int cont = 0; cont < 2; cont++){
			this.etiquetas[cont] = new JLabel();
			this.etiquetas[cont].setBorder(borde);
			this.etiquetas[cont].setFont( new Font("Helvetica", Font.BOLD, 13 ) );
			this.panel.add(etiquetas[cont]);
		}
		
	}
     
	private void PonerEtiquetasEnBlanco(){
		this.etiquetas[0].setText(" Region: ");
		this.etiquetas[1].setText(" Dinero que otorga : ");
		
	}
	
}
