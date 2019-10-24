package model.logic;

public class Zona implements Comparable<Zona> 
{

	//-----------------------------
	// Atributos
	//-----------------------------
	private String movement_id;
	private String scanombre;
	private double shape_leng;
	private double shape_area;
	private Coordenada[] coordenada;
	private Coordenada coordenadaNorte;

	//-----------------------------
	// Constructor
	//-----------------------------
	public Zona(String movement_id, String scanombre, double shape_leng, double shape_area, Coordenada[] coordenada)
	{
		this.movement_id = movement_id;
		this.scanombre = scanombre;
		this.shape_leng = shape_leng;
		this.shape_area = shape_area;
		this.coordenada = coordenada;
	}
	

	//-----------------------------
	// Getters
	//-----------------------------
	public String getMovement_id() {
		return movement_id;
	}

	public String getScanombre() {
		return scanombre;
	}

	public double getShape_leng() {
		return shape_leng;
	}

	public double getShape_area() {
		return shape_area;
	}


	@Override
	public int compareTo(Zona o)
	{	
		if(this.latitudMasNorte() < o.latitudMasNorte())
			return -1;
		else if(this.latitudMasNorte() > o.latitudMasNorte())
			return 1;
		return 0;
	}
	
	
	public double latitudMasNorte()
	{
		double maxNorte = 0;
		
		for(int i = 0; i < coordenada.length;i++)
		{
			if(coordenada[i].latitud > maxNorte)
			{
				maxNorte = coordenada[i].latitud;
				coordenadaNorte = coordenada[i];
			}
		}
		
		return maxNorte;
	}
	
	public Coordenada getCoordenadaMasNorte()
	{
		return coordenadaNorte;
	}




}
