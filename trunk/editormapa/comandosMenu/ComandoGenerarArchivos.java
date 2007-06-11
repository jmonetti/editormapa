package comandosMenu;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;
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

	public void ejecutar() {
		//si la cantidad de regiones es mayor a 0 genero el archivo de configuracion
		if(Mapa.getInstance().getCantidadRegiones() > 0){
			generarArchivoRegiones("Prueba.xml");
			//generarArchivoLimitrofes("");
			//generarArchivoIntefaz();
		}
		else
			JOptionPane.showMessageDialog(VistaPrincipal.getInstance().getJDesktopPane(),"Debe ingresar al menos una región para poder generar el archivo de configuración");
	}

	private void generarArchivoIntefaz() {
		
		
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
