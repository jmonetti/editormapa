package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;

import controlador.ControladorBotonTerminar;
import controlador.ControladorNoMover;

public class FrameAgregarRegion extends JInternalFrame {
	
	public FrameAgregarRegion(String titulo){
		//Llamo al constructor del padre
		super(titulo,false,false,false,false);
		this.setResizable(false); //Hago que no pueda cambiar su tamaño

		//Creo el panel
		JLayeredPane panel = new JLayeredPane();
		//Defino el tamaño de la pantalla
		Dimension tamanoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
		//Establezco el tamaño y las coordenadas
		panel.setBounds(tamanoPantalla.width/3, 2 * tamanoPantalla.height / 3, tamanoPantalla.width / 3, tamanoPantalla.height / 3 - 75);
	    this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
	    this.setBounds(tamanoPantalla.width/3, 2 * tamanoPantalla.height / 3, tamanoPantalla.width / 3, tamanoPantalla.height / 3 - tamanoPantalla.height / 10);
	    this.setLocation((int) (tamanoPantalla.getWidth()/3), (int) (2*tamanoPantalla.getHeight()/3));
	    
		//Creo los botones
	    this.agregarBotones(panel);
		//agrego el panel al frame
		this.getContentPane().add(panel);
		//agrego esta vista como observadora del modelo
		this.addComponentListener(new ControladorNoMover(this.getX(), this.getY()));
		this.setVisible(true);
	}

	private void agregarBotones(JLayeredPane panel) {
		JButton b = new JButton("Terminar");
		b.setBounds(0, 0, 100, 100);
		panel.add(b);
		
		b.addActionListener(new ControladorBotonTerminar());
		
	}
}
