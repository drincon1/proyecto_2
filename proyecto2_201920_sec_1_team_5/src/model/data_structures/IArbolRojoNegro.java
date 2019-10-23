package model.data_structures;

import java.util.Iterator;


public interface IArbolRojoNegro<K extends Comparable<K>,V>
{
	/**
	 * Retornar el número de parejas [Llave,Valor] del árbol
	 * @return
	 */
	int size();

	/**
	 * Informa si el árbol es vacío
	 * @return
	 */
	boolean isEmpty ();

	/**
	 * Retorna el valor V asociado a la llave key dada. 
	 * Si la llave no se encuentra se retorna el valor null.
	 * @param key
	 * @return
	 */
	V get(K key);

	/**
	 * Retorna la altura del camino desde la raiz para llegar a la llave key (si la llave existe). 
	 * Retorna valor –1 si la llave No existe.
	 * @param key
	 * @return
	 */
	int getHeight(K key);

	/**
	 * Indica si la llave key se encuentra en el árbol
	 * @param key
	 * @return
	 */
	boolean contains(K key);
	
	/**
	 * Inserta la pareja [key, val] en el árbol respetando el balanceo RedBlack. 
	 * Si la llave ya existe se reemplaza el valor. 
	 * Si la llave key o el valor val es null se debe lanzar una Exception.
	 * @param key
	 * @param val
	 */
	void put(K key, V val);
	
	/**
	 * Retorna la altura del árbol definida como la altura de la rama más alta 
	 * (aquella que tenga mayor número de enlaces desde la raíz a una hoja).
	 * @return
	 */
	int height();
	
	/**
	 * Retorna la llave más pequeña del árbol. Valor null si árbol vacío
	 * @return
	 */
	K min();
	
	/**
	 * Retorna la llave más grande del árbol. Valor null si árbol vacío
	 * @return
	 */
	K max();
	
	/**
	 * Valida si el árbol es Binario Ordenado y está balanceado Rojo- Negro a la izquierda
	 * @return
	 */
	boolean check();
	
	/**
	 * Retorna todas llaves del árbol como un iterador
	 * @return
	 */
	Iterator <K> keys();
	
	/**
	 * Retorna todos los valores V en el árbol que estén asociados al rango de llaves dado.
	 * Por eficiencia, debe intentarse No recorrer todo el árbol.
	 * @param init
	 * @param end
	 * @return
	 */
	Iterator<V> valuesInRange(K init, K end);
	
	/**
	 * Retorna todas las llaves K en el árbol que se encuentran en el rango de llaves dado. 
	 * Por eficiencia, debe intentarse No recorrer todo el árbol.
	 * @param init
	 * @param end
	 * @return
	 */
	Iterator<K> keysInRange(K init, K end);
		
	
	

}
