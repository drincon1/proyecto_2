package model.data_structures;

import model.logic.TravelTime;

public class MaxColaCP <T extends Comparable<T>> implements IMaxColaCP<T>
{
	//-----------------------------
	// Atributos
	//-----------------------------

	private int tamano;

	private ListaDoblementeEncadenada<T> lista;

	//-----------------------------
	// Constructor
	//-----------------------------
	public MaxColaCP()
	{
		tamano = 0;
		lista = new ListaDoblementeEncadenada<T>();
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
		Nodo<T> nuevoNodo = new Nodo<T>();
		Nodo<T> primeroListaDE = lista.primero;
		if (esVacia()) {
			primeroListaDE = nuevoNodo;
			nuevoNodo.cambiarElemento(elemento);
		}else {
			lista.ultimo.cambiarSiguiente(nuevoNodo);
			nuevoNodo.cambiarAnterior(lista.ultimo);
			nuevoNodo.cambiarElemento(elemento);
		}lista.ultimo = nuevoNodo;
		tamano++;
		swim(tamano);

	}

	@Override
	public T sacarMax() 
	{
		Nodo <T> actual = lista.primero;
		T maximo = null;

		if (esVacia())
			return null;

		else {
			actual = lista.primero;
			exch(1, tamano--);
			maximo = (T) lista.darElementoPorPosicion(tamano + 1);
			maximo = null;

			sink(1);
			return maximo;

		}
	}

	@Override
	public T darMax() 
	{
		if (esVacia())
			return null;
		else {
			return (T)lista.darElementoPorPosicion(0);
		}
	}

	@Override
	public boolean esVacia() 
	{
		if (tamano == 0)
			return true;
		else
			return false;
	}

	private boolean less (T elem1, T elem2){
		Nodo<T> nodoActual = lista.primero;
		boolean menor = false;
		while (nodoActual != null){
			if (elem1.compareTo(elem2) < 0)
				menor = true;
			else
				menor = false;

			nodoActual = nodoActual.darSiguiente();

		}
		return menor;
	}

	private void exch (int a, int b){

		try {
			Nodo<T> elemento1 = lista.darElementoPorPosicion(a);
			Nodo<T> elemento2 = lista.darElementoPorPosicion(b);
			Nodo<T> actual = lista.primero;

			while (actual != null){

				if (elemento1.darSiguiente() == elemento2){
					Nodo<T> temporal = elemento2.darSiguiente();
					elemento1.darAnterior().cambiarSiguiente(elemento2);
					elemento2.cambiarAnterior(elemento1.darAnterior());
					elemento1.cambiarSiguiente(temporal);
					temporal.cambiarAnterior(elemento1);
					elemento2.cambiarSiguiente(elemento1);
					elemento1.cambiarAnterior(elemento2);
				}else if (elemento2.darSiguiente() == elemento1){
					Nodo<T> temp = elemento1.darSiguiente();
					elemento2.darAnterior().cambiarSiguiente(elemento1);
					elemento1.cambiarAnterior(elemento2.darAnterior());
					elemento2.cambiarSiguiente(temp);
					temp.cambiarAnterior(elemento2);
					elemento1.cambiarSiguiente(elemento2);
					elemento2.cambiarAnterior(elemento1);
				}

				actual = actual.darSiguiente();
			}
		}catch (Exception r) {
			r.printStackTrace();
		}
	}

	private void swim (int i){
		while (i > 1 && less ((T) lista.darElementoPorPosicion(i/2), (T) lista.darElementoPorPosicion(i))){
			exch(i, i/2);
			i = i/2;
		}
	}

	private void sink (int j){
		T betha = null;
		T phi = null;
		while (2*j <= tamano){
			int alpha = 2*j;
			betha = (T)lista.darElementoPorPosicion(alpha);
			phi = (T)lista.darElementoPorPosicion(alpha + 1);
			if (alpha < tamano && less(betha, phi))
				alpha++;
			if (!less((T)lista.darElementoPorPosicion(j), betha)) break;
			exch(j, alpha);

			j = alpha;
		}
	}

}
