package model.data_structures;
import javax.swing.*;
import java.util.*;
import java.awt.*;

public class Nodo <T> {

	private static final long serialVersionUID = 1L;


	//Primer nodo de la lista
	private Nodo <T> primero;

	//Nodo siguiente de la lista
	public Nodo <T> siguiente;
	
	//Nodo anterior de la lista
	public Nodo<T> anterior;

	//Elemnto actual de la lista
	public T elemento;

	//-------------------------------------------------------
	//Contructor
	//-------------------------------------------------------

	/**
	 * Inicializa la clase Nodo
	 * @param elemento que se va a agregar a la Lista
	 */
	public Nodo (T elemento)
	{
		this.elemento = elemento;
	}
	public Nodo()
	{

		siguiente = null;
	}



	/**
	 * Retorna el elemento siguiente de la lista
	 */
	public Nodo <T> darSiguiente () {
		return siguiente;
	}
	
	/**
	 * Retorna el elemento anterior de la lista
	 */
	public Nodo <T> darAnterior (){
		return anterior;
	}

	/**
	 * Retorna el primer elemento de la lista
	 */
	public Nodo <T> darPrimero () {
		return primero;
	}

	/**
	 * permite cambiar el anterior elemento
	 */
	public void cambiarSiguiente (Nodo <T> siguiente) {
		this.siguiente = siguiente;
	}
	
	public void cambiarAnterior (Nodo <T> anterior){
		this.anterior = anterior;
	}


	/**
	 * Retorna el elemento de la lista
	 */
	public T darElemento () {
		return elemento;
	}

	/**
	 * Permite actualizar el elemento
	 */
	public void cambiarElemento (T elemento) {
		this.elemento = elemento;
	}
}
