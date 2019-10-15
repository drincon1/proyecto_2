package model.data_structures;
//import static org.junit.Assert.*;

//import org.junit.Before;
//import org.junit.Test;
/**
 * Clase para una lista doblemente encadenada generica
 * Los  metodos fueron sacados de las siguientes paginas;
 * https://letstalkalgorithms.com/doubly-linkedlist-implementation-java/
 * https://www.geeksforgeeks.org/doubly-linked-list/
 * http://www.java2novice.com/data-structures-in-java/linked-list/doubly-linked-list/
 * https://study.com/academy/lesson/doubly-linked-lists-in-java-creation-nodes.html
 * https://codereview.stackexchange.com/questions/122535/java-doubly-linked-list-implementation
 * @author Daniel Rincon Garcia
 * @param <T>
 */
public class ListaDoblementeEncadenada<T extends Comparable<T>> 
{
	//Tamaño de la lista doblemente encadenada
	int tamano;

	//Primer nodo de la lista doblemente encadenada
	Nodo<T> primero;

	//Último nodo de la lista doblemente encadenada
	Nodo<T> ultimo;

	/**
	 * Constructor de la lista doblemente encadenada
	 * Primero y ultimo se incializan como null
	 * El tamano comienza en 0
	 */
	public ListaDoblementeEncadenada()
	{
		primero = null;
		ultimo = null;
		tamano = 0;
	}

	/**
	 * Devuelve el tamano de la lista doblemente encadenada
	 * @return tamano. Int que representa el tamaño de la lista doblemente encadenada
	 */
	public int darTamano()
	{
		return tamano;
	}

	/**
	 * Agrega un nuevo elemento a la lista doblemente encadenada
	 * @param elem Elemento que se va a agregar. elem != null
	 */
	public void agregar(T elem)
	{
		Nodo<T> temp = new Nodo<T>(elem);
		if(primero == null)
			primero = temp;
		else
		{
			ultimo.siguiente = temp;
			temp.anterior = ultimo;
		}
		//El ultimo se convierte en el elemento nuevo
		ultimo = temp;
		tamano++;
	}

	/**
	 * Elimina un elemento especifico de la lista doblemente encadenada
	 * @param elem Elemento que se va eliminar. elem != null
	 */
	public void eliminar(T elem)
	{
		Nodo<T> temp = primero;
		while(temp != null)
		{
			if(temp.darElemento().compareTo(elem) == 0)
			{
				//Si el elemento que se va a eliminar es el ultimo toca hacer cambios diferentes
				if(temp.siguiente == null)
				{
					ultimo = temp.anterior;
					temp.anterior.siguiente = temp.siguiente;
					temp.siguiente.anterior = temp.anterior;
				}
				else
				{
					temp.anterior.siguiente = temp.siguiente;
					temp.siguiente.anterior = temp.anterior;
				}
				tamano--;
			}
		}
	}

	/**
	 * El método devuelve si la lista doblemente encadenada esta vacia o no
	 * @return true si la lista esta vacia
	 * 		   false si la lista tiene al menos un elemento
	 */
	public boolean isEmpty()
	{
		return primero == null;
	}

	/**
	 * Devuelve el primer nodo de la lista doblemente encadenada
	 * @return primero
	 */
	public Nodo<T> darPrimero()
	{
		return primero;
	}

	/**
	 * Devuelve el ultimo nodo de la lista doblemente encadenada
	 * @return ultimo
	 */
	public Nodo<T> darUltimo()
	{
		return ultimo;
	}

	public Nodo<T> darElementoPorPosicion(int posicion)
	{
		Nodo<T> temporal = primero;
		
		boolean encontrado = false;
		int i = 0;
		
		if(posicion == 0)
			return primero;
		
		if(posicion == tamano -1)
			return ultimo;
		
		while(!encontrado && i < tamano)
		{
			if(i == posicion)
			{
				encontrado = true;
				break;
			}
			i++;
			temporal = temporal.siguiente;
		}

		if(!encontrado)
			return null;
		
		return temporal;
	}

}
