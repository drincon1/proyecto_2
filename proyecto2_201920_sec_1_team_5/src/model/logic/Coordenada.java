package model.logic;

public class Coordenada 
{

	public double latitud;
	public double longitud;
	
	public Coordenada(double latitud, double longitud) 
	{
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public double getLatitud() {
		return latitud;
	}

	public double getLongitud() {
		return longitud;
	}

}
