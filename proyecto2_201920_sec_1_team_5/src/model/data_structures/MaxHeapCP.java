package model.data_structures;

public class MaxHeapCP <T extends Comparable<T>> implements IMaxHeapCP<T>
{
	//-----------------------------
	// Atributos
	//-----------------------------
	private int tamano;

	private T[] arreglo;

	//-----------------------------
	// Constructor
	//-----------------------------

	public MaxHeapCP(int maxN)
	{
		arreglo = (T[]) new Comparable[maxN + 1];
		tamano  = 0;
	}

	//-----------------------------
	// Métodos de funcionamiento
	//-----------------------------
	@Override
	public int darNumElementos() 
	{
		return tamano;
	}

	@Override
	public void agregar(T elemento) 
	{
		arreglo[++tamano] = elemento;
		swim(tamano);
	}

	@Override
	public T sacarMax() 
	{
		if(esVacia())
			return null;

		T max = arreglo[1];
		exch(1, tamano--);
		arreglo[tamano + 1] = null;
		sink(1);

		return max;
	}

	@Override
	public T darMax() 
	{
		if(esVacia())
			return null;

		return arreglo[1];
	}

	@Override
	public boolean esVacia() 
	{
		return tamano == 0;
	}
	
	public T darPorPosicion(int i)
	{
		if(i > tamano)
			return null;
		else
		{
			return arreglo[i];
		}
	}

	//-----------------------------
	// Métodos básicos de Heap
	//-----------------------------

	private boolean less(int i, int j)
	{  
		return arreglo[i].compareTo(arreglo[j]) < 0;  
	}

	private void exch(int i, int j)
	{  
		T t = arreglo[i];
		arreglo[i] = arreglo[j]; 
		arreglo[j] = t;  
	}

	private void swim (int k)
	{
		while (k > 1 && less(k/2, k))
		{
			exch(k/2, k);
			k = k/2; 
		}
	}

	private void sink(int k)
	{
		while (2*k <= tamano)
		{
			int j = 2*k;
			if (j < tamano && less(j, j+1)) 
				j++;
			if (!less(k, j)) 
				break;

			exch(k, j);
			k = j;
		} }

	@Override
	public int darElementosPosibles() 
	{
		return arreglo.length-1;
	}


}

