package model.logic;

public class TravelTime implements Comparable<TravelTime>
{

	public enum TipoTiempo
	{
		HORA,
		DIA,
		MES;
	}
	//-----------------------------
	// Atributos
	//-----------------------------
	
	// es el trimestre del año del viaje
	private int trimestre;
	
	//es la zona de origen
	private int sourceid;
	
	//es la zona destino
	private int distid;
	
	//es la hora del dia o el día de la semana o el mes del año
	private int tiempo;
	
	//es el tiempo promedio de viaje entre las dos zonas
	private double mean_travel_time;
	
	//es la desviación estándar de los tiempos de viaje
	private double standard_deviation_travel_time;
	
	private TipoTiempo tipoTiempo;

	
	//-----------------------------
	// Constructor
	//-----------------------------
	/**
	 * Metodo constructor de la clase TravelTime
	 * @param trimestre El trimestre del año del viaje. trimestre == 1 || trimestre == 2
	 * @param sourceid La zona de origen source. sourceid > 0
	 * @param distid La zona destino. distid > 0  
	 * @param hod La hora del dia. hod >=0 && hod <= 23
	 * @param mean_travel_time El tiempo promedio de viaje entre las dos zonas. mean_travel_time > 0
	 * @param standard_deviation_travel_time La desviación estándar de los tiempos de viaje. standar_deviation_travel_time > 0
	 */
	public TravelTime(int trimestre, int sourceid, int distid, int tiempo, double mean_travel_time,
			double standard_deviation_travel_time, TipoTiempo tipoTiempo) 
	{
		this.trimestre = trimestre;
		this.sourceid = sourceid;
		this.distid = distid;
		this.tiempo = tiempo;
		this.mean_travel_time = mean_travel_time;
		this.standard_deviation_travel_time = standard_deviation_travel_time;
		this.tipoTiempo = tipoTiempo;
	}


	//-----------------------------
	// Metodos getters
	//-----------------------------

	public int getTrimestre()
	{
		return trimestre;
	}
	
	public int getSourceid() {
		return sourceid;
	}
	
	public int getDistid() {
		return distid;
	}
	
	public int getTiempo() {
		return tiempo;
	}

	public double getMean_travel_time() {
		return mean_travel_time;
	}
	
	public double getStandard_deviation_travel_time() {
		return standard_deviation_travel_time;
	}
	
	public TipoTiempo getTipoTiempo() {
		return tipoTiempo;
	}

	//-----------------------------
	// Metodos de funcionamiento 
	//-----------------------------
	@Override
	public int compareTo(TravelTime o) 
	{
		if(this.mean_travel_time > o.mean_travel_time)
			return 1;
		else if(this.mean_travel_time < o.mean_travel_time)
			return -1;
		return 0;
	}


}
