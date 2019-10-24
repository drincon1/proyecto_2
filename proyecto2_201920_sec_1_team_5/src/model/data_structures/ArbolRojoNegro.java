package model.data_structures;

import java.util.ArrayList;
import java.util.Iterator;

public class ArbolRojoNegro<K extends Comparable<K>,V> implements IArbolRojoNegro<K,V>
{
	//-------------------------------------------------
	// Atributos
	//-------------------------------------------------

	private static final boolean RED = true;
	private static final boolean BLACK = false;
	private Node<K,V> root;
	public ArrayList<K> llaves;
	public int size;



	//-------------------------------------------------
	// Constructor
	//-------------------------------------------------

	public ArbolRojoNegro()
	{
		root = null;
		llaves = new ArrayList<K>();
		size = 0;

	}

	//-------------------------------------------------
	// Métodos
	//-------------------------------------------------
	@Override
	public int size() 
	{
		return size;
	}

	/**
	 * Número de nodos que están en ese subarbol
	 * @param x Nodo que se comporta como raiz del subarbol
	 * @return 
	 */
	private int size(Node<K,V> x)
	{
		if(x == null) 
			return 0;
		else
			return x.N;
	}

	@Override
	public boolean isEmpty() 
	{
		return root == null;
	}

	@Override
	public V get(K key) 
	{
		if(root != null)
			return get(root, key);
		return null;
	}

	//Algoritmo usado de:
	//https://algs4.cs.princeton.edu/33balanced/RedBlackBST.java.html
	private V get(Node<K,V> x, K key)
	{
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if      (cmp < 0) x = x.izq;
			else if (cmp > 0) x = x.der;
			else              return x.value;
		}
		return null;
	}

	@Override
	public int getHeight(K key) 
	{
		if(root != null)
			return getHeight(root, key);
		return -1;
	}

	private int getHeight(Node<K,V> h, K key)
	{
		int height = 0;
		while(h != null)
		{
			int cmp = key.compareTo(h.key);
			if(cmp < 0) 
			{
				h = h.izq;
				if(h.color == BLACK)
					height++;
			}
			else if(cmp > 0) 
			{
				h = h.der;
				height++;
			}
			else return height;
		}
		return -1;
	}


	@Override
	public boolean contains(K key) 
	{
		return (get(key) != null);
	}

	@Override
	public void put(K key, V val) 
	{
		root = put(root, key, val);
		root.color = BLACK;
		llaves.add(key);
		size++;
	}

	private Node<K,V> put(Node<K,V> h, K key, V val)
	{
		if(h == null)
			return new Node<K,V>(key, val, 1, RED);
		int cmp = key.compareTo(h.key);
		if      (cmp < 0) h.izq  = put(h.izq,  key, val);
		else if (cmp > 0) h.der = put(h.der, key, val);
		else h.value = val;

		if (isRed(h.der) && !isRed(h.izq))    h = rotateLeft(h);
		if (isRed(h.izq) && isRed(h.izq.izq)) h = rotateRight(h);
		if (isRed(h.izq) && isRed(h.der))     flipColors(h);
		h.N = size(h.izq) + size(h.der) + 1;

		return h;
	}

	@Override
	public int height() 
	{
		if(root != null)
			return height(root);
		return -1;
	}
	private int height(Node<K,V> h)
	{
		if(h == null)
			return -1;

		int height = 0;
		int heightDer = height(h.der);
		int heightIzq = height(h.izq);

		if(heightDer > heightIzq)
			height = heightDer;
		else
			height = heightIzq;

		if(h.color == BLACK)
			return height + 1;
		else
			return height;
	}


	@Override
	public K min() 
	{
		if(!isEmpty())
			return min(root);
		return null;
	}

	private K min(Node<K,V> h)
	{
		K min = null;
		while(h != null)
		{
			min = h.key;
			h = h.izq;

		}
		return min;
	}

	@Override
	public K max() 
	{
		if(!isEmpty())
			return max(root);
		return null;
	}
	private K max(Node<K,V> h)
	{
		K max = null;
		while(h != null)
		{
			max = h.key;
			h = h.der;

		}
		return max;
	}

	@Override
	public Iterator<K> keys() 
	{
		return llaves.iterator();
	}


	@Override
	public Iterator<V> valuesInRange(K init, K end) 
	{
		return null;
	}

	@Override
	public Iterator<K> keysInRange(K init, K end) 
	{
		return null;
	}

	@Override
	public boolean check() {
		return false;
	}

	private boolean isRed(Node<K,V> x)
	{
		if(x == null) return false;
		return x.color == RED;
	}
	


	//-------------------------------------------------
	// Metodos de rotación
	//-------------------------------------------------
	private Node<K,V> rotateLeft(Node<K,V> h)
	{
		Node<K,V> x = h.der;
		h.der = x.izq;
		x.izq = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1+ size(h.izq) + size(h.der);
		return x;
	}
	private Node<K,V> rotateRight(Node<K,V> h)
	{
		Node<K,V> x = h.izq;
		h.izq = x.der;
		x.der = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.izq)
		+ size(h.der);
		return x;
	}
	private void flipColors(Node<K,V> h)
	{
		h.color = RED;
		h.izq.color = BLACK;
		h.der.color = BLACK;
	}








}
