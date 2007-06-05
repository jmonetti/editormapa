package modelo;

import java.util.Comparator;
/**
 * @author Grupo 2, Algoritmos y programación 3, Cátedra Fontela<br>1er cuat 2007
 * <br>Este comparaddor permite comparar por id de las regiones
 * @see Comparator
 */
public class ComparadorRegionesId implements Comparator {
	
	public int compare(Object arg0, Object arg1) {
		Region reg1 = (Region) arg0;
		Region reg2 = (Region) arg1;
		if (reg1.equals(reg2))
			return 0;
		else
			return -1;
	}
	
}
