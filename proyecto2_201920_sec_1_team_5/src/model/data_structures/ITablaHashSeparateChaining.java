package model.data_structures;

import java.util.*;

public interface ITablaHashSeparateChaining <K, V extends Comparable<V>>
{
	/**
	 * Agregar una dupla (K, V) a la tabla.
	 * Si la llave K existe, se reemplaza su valor V asociado. 
	 * V no puede ser null.
	 * @param key Identificador en la tabla hash. key != null 
	 * @param value Elemento que esta asociado a key. value != null
	 */
	void put(K key, V value);
	
	/**
	 * Obtener el valor V asociado a la llave K. 
	 * Se obtiene null solo si la llave K no existe.
	 * @param key La llave asociada al elemento que se esta buscando. k != null
	 * @return
	 */
	V get(K key);
	
	/**
	 * Borrar la dupla asociada a la llave K. 
	 * Se obtiene el valor V asociado a la llave K. 
	 * Se obtiene null solo si la llave K no existe.
	 * @param key La llave asociada al elemento que se va a borrar. k != null
	 * @return
	 */
	V delete(K key);
	
	/**
	 * Conjunto de llaves K presentes en la tabla.
	 * @return Un iterador con las llaves de la tabla
	 */
	Iterator<K> keys();
	
	
}
