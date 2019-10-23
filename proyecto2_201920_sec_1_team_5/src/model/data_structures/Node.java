package model.data_structures;

public class Node<K extends Comparable<K>,V>
{
	//Llave asociada al nodo
	K key;
	//Valor asociado al nodo
	V value;
	//Los 'hijos' izquierdo y derecho
	Node<K,V> izq,der;
	//Número de nodos en ese subarbol
	int N;
	//color del enlaze del padre a este nodo
	boolean color;

	public Node(K key, V value, int N,boolean color) 
	{
		this.key = key;
		this.value = value;
		this.N = N;
		this.color = color;
	}	

	private boolean esHoja()
	{
		return (der == null && izq == null);
	}
	
	public int size()
	{
		int size = 0;
		
		if(esHoja())
			return 1;
		else
		{
			if(der != null)
				size += der.size();
			else if(izq != null)
				size += izq.size();
		}
		return size + 1;
	}
}
