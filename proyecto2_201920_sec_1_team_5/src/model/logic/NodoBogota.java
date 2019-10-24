package model.logic;

public class NodoBogota implements Comparable<NodoBogota> 
{

	//---------------------------------
	// Atributos
	//---------------------------------
	private int id;
	private double latitud;
	private double longitud;


	//---------------------------------
	// Constructor
	//---------------------------------
	public NodoBogota(int id, double longitud, double latitud) 
	{
		this.id = id;
		this.longitud = longitud;
		this.latitud = latitud;
	}

	//---------------------------------
	// Funcionamiento
	//---------------------------------
	@Override
	public int compareTo(NodoBogota o) 
	{
		if(this.latitud < o.latitud)
			return -1;
		else if(this.latitud > o.latitud)
			return 1;
		return 0;
	}

	//---------------------------------
	// Getters
	//---------------------------------
	public int getId() {
		return id;
	}

	public double getLatitud() {
		return latitud;
	}

	public double getLongitud() {
		return longitud;
	}







}
