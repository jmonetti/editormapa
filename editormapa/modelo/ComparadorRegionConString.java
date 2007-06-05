package modelo;

import java.util.Comparator;

/**
 * @author Grupo 2, Algoritmos y programaci�n 3, C�tedra Fontela<br>1er cuat 2007
 * <br>Este comparaddor permite comparar el nombre de una region con un string. Esto es muy
 * �til para buscar por ejemplo
 * @see Comparator
 */
public class ComparadorRegionConString implements Comparator {

	public int compare(Object arg0, Object arg1) {
		String cadena = (String) arg1;
		Region reg = (Region) arg0;
		return reg.getNombre().compareTo(cadena);
	}

}
