package modelo;

import java.util.Comparator;

/**
 * @author Grupo 2, Algoritmos y programación 3, Cátedra Fontela<br>1er cuat 2007
 * <br>Este comparaddor permite comparar el id de las regiones con un integer. Esto es muy
 * útil para buscar por ejemplo
 * @see Comparator
 */

public class ComparadorIdRegionConInt implements Comparator {

	public int compare(Object arg0, Object arg1) {
		Region region = (Region)arg0;
		Integer id =(Integer)arg1;
		if(region.getId()== id.intValue())
			return 0;
		else
			return -1;
	}

}
