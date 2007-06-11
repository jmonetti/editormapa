package comandosMenu;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

import com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;


import modelo.Mapa;
import vista.VistaPrincipal;

public class ComandoGenerarArchivos extends ComandoMenu {

	private final static String nombreArchivoConfig = "config.raj";
	private final static String nombreArchivoRegiones = "Regiones.xml";
	private final static String nombreArchivoLimitrofes = "Limitrofes.xml";
	private final static String nombreArchivoVista = "Regiones-vista.xml";
	
	public void ejecutar() {
	
		
		//si la cantidad de regiones es mayor a 0 genero el archivo de configuracion
		if(Mapa.getInstance().getCantidadRegiones() > 1){
			JFileChooser ventanaArchivos = new JFileChooser();
			ventanaArchivos.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			//ventanaArchivos.setAcceptAllFileFilterUsed(false);
			int opcion = ventanaArchivos.showSaveDialog(VistaPrincipal.getInstance().getJDesktopPane());
			if(opcion == JFileChooser.APPROVE_OPTION){
				generarArchivoConfiguracion(ventanaArchivos.getSelectedFile().getAbsolutePath()+ "/" + nombreArchivoConfig);
				generarArchivoRegiones(ventanaArchivos.getSelectedFile().getAbsolutePath()+ "/" + nombreArchivoRegiones);
				generarArchivoLimitrofes(ventanaArchivos.getSelectedFile().getAbsolutePath() + "/" + nombreArchivoLimitrofes);
				generarArchivoIntefaz(ventanaArchivos.getSelectedFile().getAbsolutePath()+ "/" + "/" +nombreArchivoVista);
			}
		}
		else
			JOptionPane.showMessageDialog(VistaPrincipal.getInstance().getJDesktopPane(),"Debe ingresar al menos dos regiones para poder generar el archivo de configuración");
	}

	private void generarArchivoConfiguracion(String string) {
		
		
	}

	private void generarArchivoIntefaz(String ruta) {
		
		
	}

	private void generarArchivoLimitrofes(String ruta) {
		Document xmlDoc = generarDocumentoXml();
		xmlDoc.appendChild(Mapa.getInstance().limitrofesToXml(xmlDoc));
		imprimirAArchivo(xmlDoc, ruta);
		
	}
	private void generarArchivoRegiones(String ruta) {
		Document xmlDoc = generarDocumentoXml();
		xmlDoc.appendChild(Mapa.getInstance().regionesToXml(xmlDoc));
		imprimirAArchivo(xmlDoc, ruta);
	}
	private Document generarDocumentoXml(){
		DocumentBuilderFactory dbFactory = DocumentBuilderFactoryImpl.newInstance();
	    DocumentBuilder docBuilder = null;
		try {
			docBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	    Document  xmlDoc = docBuilder.newDocument();
	    return xmlDoc;
	}
	private void imprimirAArchivo(Document dom, String rutaArchivo){

		try
		{
			//defino el formato al cual voy a imprimri el archivo
			OutputFormat format = new OutputFormat(dom);
			format.setIndenting(true);

			//Genero un serializador de Dom para los xml y le paso el archivo destino
			XMLSerializer serializer = new XMLSerializer(
			new FileOutputStream(new File(rutaArchivo)), format);
			//serializo el documento pasado por parametro
			serializer.serialize(dom);

		} catch(IOException ie) {
		    ie.printStackTrace();
		}
	}
	
	
}
