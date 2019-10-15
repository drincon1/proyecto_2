package model.data_structures;

/**
 * 2019-01-23
 * Estructura de Datos Arreglo Dinamico de Strings.
 * El arreglo al llenarse (llegar a su maxima capacidad) debe aumentar su capacidad.
 * @author Fernando De la Rosa
 *
 */
public class ArregloDinamico<T extends Comparable<T>> implements IArregloDinamico<T> {
	/**
	 * Capacidad maxima del arreglo
	 */
	private int tamanoMax;
	/**
	 * Numero de elementos presentes en el arreglo (de forma compacta desde la posicion 0)
	 */
	private int tamanoAct;
	/**
	 * Arreglo de elementos de tamaNo maximo
	 */
	private T elementos[ ];

	/**
	 * Construir un arreglo con la capacidad maxima inicial.
	 * @param max Capacidad maxima inicial
	 */
	public ArregloDinamico( int max )
	{
		elementos = (T[]) new Comparable [max];
		tamanoMax = max;
		tamanoAct = 0;
	}

	public void agregar( T dato )
	{
		if ( tamanoAct == tamanoMax )
		{  // caso de arreglo lleno (aumentar tamaNo)
			tamanoMax = 2 * tamanoMax;
			T [ ] copia = elementos;
			elementos = (T[]) new Comparable [tamanoMax];
			for ( int i = 0; i < tamanoAct; i++)
			{
				elementos[i] = copia[i];
			} 
			System.out.println("Arreglo lleno: " + tamanoAct + " - Arreglo duplicado: " + tamanoMax);
		}	
		elementos[tamanoAct] = dato;
		tamanoAct++;
	}
	
	public void agregarPorPosicion(T dato, int pos)
	{
		elementos[pos] = dato;
		tamanoAct++;
	}

	public int darCapacidad() {
		return tamanoMax;
	}

	public int darTamano() {
		return tamanoAct;
	}


	public T darElemento(int i) 
	{
		return elementos[i];
	}

	/**
	 * Buscar un dato en el arreglo.
	 * @param dato Objeto de busqueda en el arreglo
	 * @return elemento encontrado en el arreglo (si existe). null si no se encontro el dato.
	 */
	public T buscar(T dato) {
		//CHECK
		// Recomendacion: Usar el criterio de comparacion natural (metodo compareTo()) definido en Strings.
		T elemento = null;
		boolean ubicado = false;
		for(int i = 0; i < elementos.length && !ubicado; i++)
		{
			if(elementos[i].compareTo(dato) == 0)
			{
				elemento = elementos[i];
				ubicado = true;
			}
		}
		return elemento;
	}

	/**
	 * Eliminar un dato del arreglo.
	 * Los datos restantes deben quedar "compactos" desde la posicion 0.
	 * @param dato Objeto de eliminacion en el arreglo
	 * @return dato eliminado
	 */
	public T eliminar(T dato)
	{
		T elemento = buscar(dato);
		if(elemento != null)
		{
			T[] copia = (T[]) new Comparable[tamanoAct - 1];
			int i = 0;
			while(i < elementos.length)
			{
				if(elementos[i].compareTo(dato) != 0)
				{
					copia[i] = elementos[i];
					i++;
				}
				else
				{
					elemento = elementos[i];
					i++;
					tamanoAct -= 1;
				}
			}
		}
		return elemento;
	}

	public boolean isEmpty()
	{
		if(elementos[0] == null)
			return true;
		
		return false;
	}
	
	public void rehash()
	{
		tamanoMax = 2 * tamanoMax;
		elementos = (T[]) new Comparable [tamanoMax];
	}

}
