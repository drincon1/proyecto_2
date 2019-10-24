package model.data_structures;

/**
 * Cola de Prioridad basada en la representación de un Heap
 * @author Daniel Rincon
 * @param <T> Tipo de elemento
 */
public interface IMaxHeapCP <T extends Comparable<T>>
{
	/**
	 * Retorna número de elementos presentes en la cola de prioridad
	 * @return Número de elementos
	 */
	int darNumElementos();
	
	/**
	 * Agrega un elemento a la cola de prioridad.
	 * @param elemento Elemento a agregar. elemento != null
	 */
	void agregar(T elemento);
	
	/**
	 * Saca/atiende el elemento máximo en la cola y lo retorna.
	 * null en caso de cola vacía
	 * @return 
	 */
	T sacarMax ();
	
	/**
	 * Obtener el elemento máximo (sin sacarlo de la Cola)
	 * null en caso de cola vacía
	 * @return
	 */
	T darMax();
	
	/**
	 * Retorna si la cola está vacía o no
	 * @return
	 */
	boolean esVacia ();
	
	/**
	 * Devuelve cuantos elementos puede tener el arreglo
	 * @return
	 */
	int darElementosPosibles();
	
}
