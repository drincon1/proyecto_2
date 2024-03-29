package model.logic;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.decimal4j.util.DoubleRounder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.opencsv.CSVReader;

import model.data_structures.ArbolRojoNegro;
import model.data_structures.ListaDoblementeEncadenada;
import model.data_structures.MaxHeapCP;
import model.data_structures.Nodo;
import model.data_structures.TablaHashSeparateChaining;
import model.logic.TravelTime.TipoTiempo;

public class MVCModelo 
{
	//----------------------------------------------
	// Atributos
	//----------------------------------------------
	private ListaDoblementeEncadenada<TravelTime> viajesHourly1;
	private ListaDoblementeEncadenada<TravelTime> viajesHourly2;
	private ListaDoblementeEncadenada<TravelTime> viajesWeekly1;
	private ListaDoblementeEncadenada<TravelTime> viajesWeekly2;
	private ListaDoblementeEncadenada<TravelTime> viajesMonthly1;
	private ListaDoblementeEncadenada<TravelTime> viajesMonthly2;
	private int trimestre;
	private ListaDoblementeEncadenada<NodoBogota> mapaBogota;

	private ListaDoblementeEncadenada<Zona> zonas;

	//----------------------------------------------
	// Constructor
	//----------------------------------------------

	public MVCModelo()
	{
		viajesHourly1 = new ListaDoblementeEncadenada<TravelTime>();
		viajesHourly2 = new ListaDoblementeEncadenada<TravelTime>();
		viajesWeekly1 = new ListaDoblementeEncadenada<TravelTime>();
		viajesWeekly2 = new ListaDoblementeEncadenada<TravelTime>();
		viajesMonthly1 = new ListaDoblementeEncadenada<TravelTime>();
		viajesMonthly2 = new ListaDoblementeEncadenada<TravelTime>();
		trimestre = 0;
		mapaBogota = new ListaDoblementeEncadenada<NodoBogota>();
		zonas  = new ListaDoblementeEncadenada<Zona>();
	}

	//----------------------------------------------
	// Cargar información
	//----------------------------------------------

	public void cargar(int trimestre) throws IOException
	{
		this.trimestre = trimestre;

		CSVReader reader = null;
		try
		{
			//Carga del primer semestre
			String ruta =  "./data/bogota-cadastral-2018-1-All-HourlyAggregate.csv";
			reader = new CSVReader(new FileReader(ruta));
			String [] nextLine;
			nextLine = reader.readNext();
			long startTime = System.currentTimeMillis();
			while((nextLine = reader.readNext()) != null)
			{
				viajesHourly1.agregar(new TravelTime(1, Integer.parseInt(nextLine[0]), Integer.parseInt(nextLine[1]), 
						Integer.parseInt(nextLine[2]), Double.parseDouble(nextLine[3]), Double.parseDouble(nextLine[4]), TipoTiempo.HORA));
			}
			long endTime = System.currentTimeMillis();
			long duration = (endTime - startTime) / 1000;
			System.out.println("****************************\nCargó la información del trimestre 1 [horas] en " + duration +" segundos");
			ruta = "./data/bogota-cadastral-2018-1-WeeklyAggregate.csv";
			reader = new CSVReader(new FileReader(ruta));
			nextLine = reader.readNext(); 
			startTime = System.currentTimeMillis();
			while((nextLine = reader.readNext()) != null)
			{
				viajesWeekly1.agregar(new TravelTime(1, Integer.parseInt(nextLine[0]), Integer.parseInt(nextLine[1]), 
						Integer.parseInt(nextLine[2]), Double.parseDouble(nextLine[3]), Double.parseDouble(nextLine[4]),TipoTiempo.DIA));
			}
			endTime = System.currentTimeMillis();
			duration = (endTime - startTime) / 1000;
			System.out.println("Cargó la información del trimestre 1 [días] en  " + duration +" segundos");

			ruta = "./data/bogota-cadastral-2018-1-All-MonthlyAggregate.csv";
			reader = new CSVReader(new FileReader(ruta));
			nextLine = reader.readNext();
			startTime = System.currentTimeMillis();
			while((nextLine = reader.readNext()) != null)
			{
				viajesMonthly1.agregar(new TravelTime(1, Integer.parseInt(nextLine[0]), Integer.parseInt(nextLine[1]), 
						Integer.parseInt(nextLine[2]), Double.parseDouble(nextLine[3]), Double.parseDouble(nextLine[4]), TipoTiempo.MES));
			}
			endTime = System.currentTimeMillis();
			duration = (endTime - startTime) / 1000;
			System.out.println("Cargó la información del trimestre 1 [meses]  en " + duration +" segundos\n****************************");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			reader.close();
			//cargarHora2();
			cargarTrimestre2();
		}

	}

	public void cargarTrimestre2() throws IOException
	{
		CSVReader reader = null;
		try
		{ 
			String ruta =  "./data/bogota-cadastral-2018-2-WeeklyAggregate.csv";
			reader = new CSVReader(new FileReader(ruta));
			String [] nextLine;
			nextLine = reader.readNext();
			long startTime = System.currentTimeMillis();
			while((nextLine = reader.readNext()) != null)
			{
				viajesWeekly2.agregar(new TravelTime(2, Integer.parseInt(nextLine[0]), Integer.parseInt(nextLine[1]), 
						Integer.parseInt(nextLine[2]), Double.parseDouble(nextLine[3]), Double.parseDouble(nextLine[4]),TipoTiempo.DIA));
			}
			long endTime = System.currentTimeMillis();
			long duration = (endTime - startTime) / 1000;
			System.out.println("Cargó la información del trimestre 2 [días] en  " + duration +" segundos");


			ruta =  "./data/bogota-cadastral-2018-2-All-MonthlyAggregate.csv";
			reader = new CSVReader(new FileReader(ruta));
			nextLine = reader.readNext();
			startTime = System.currentTimeMillis();
			while((nextLine = reader.readNext()) != null)
			{
				viajesMonthly2.agregar(new TravelTime(2, Integer.parseInt(nextLine[0]), Integer.parseInt(nextLine[1]), 
						Integer.parseInt(nextLine[2]), Double.parseDouble(nextLine[3]), Double.parseDouble(nextLine[4]),TipoTiempo.MES));
			}
			endTime = System.currentTimeMillis();
			duration = (endTime - startTime) / 1000;
			System.out.println("Cargó la información del trimestre 2 [meses]  en " + duration +" segundos\n****************************");



		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			reader.close();
		}
	}

	public void cargarHora2() throws IOException
	{
		CSVReader reader = null;
		double duration = -1;
		try
		{
			String ruta =  "./data/bogota-cadastral-2018-2-All-HourlyAggregate.csv";
			reader = new CSVReader(new FileReader(ruta));
			String[] nextLine;
			nextLine = reader.readNext();
			long startTime = System.currentTimeMillis();
			while((nextLine = reader.readNext()) != null)
			{
				viajesHourly2.agregar(new TravelTime(1, Integer.parseInt(nextLine[0]), Integer.parseInt(nextLine[1]), 
						Integer.parseInt(nextLine[2]), Double.parseDouble(nextLine[3]), Double.parseDouble(nextLine[4]), TipoTiempo.HORA));
			}
			long endTime = System.currentTimeMillis();
			duration = (endTime - startTime) / 1000;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			reader.close();
			System.out.println("Cargó la información del trimestre 2 [horas] en " + duration +" segundos");
		}
	}



	public void cargarMapa() throws IOException
	{
		CSVReader reader = null;
		try
		{
			reader = new CSVReader(new FileReader("./data/Nodes_of_red_vial-wgs84_shp.txt"));
			String[] nextLine;
			while((nextLine = reader.readNext()) != null)
			{
				mapaBogota.agregar(new NodoBogota(Integer.parseInt(nextLine[0]), Double.parseDouble(nextLine[1]), Double.parseDouble(nextLine[2])));
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			reader.close();
		}
	}

	public void cargarJSON() throws IOException
	{
		String ruta = "./data/bogota_cadastral.json";
		File file = new File(ruta);
		JSONParser parser = new JSONParser();
		FileReader reader = null;
		try
		{
			reader = new FileReader(file);
			Object obj = parser.parse(reader);
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray features = (JSONArray)jsonObject.get("features");

			for(int i = 0; i < features.size(); i++)
			{
				JSONObject elemento = (JSONObject) features.get(i);
				JSONObject geometry = (JSONObject) elemento.get("geometry");
				JSONArray coordinates = (JSONArray) geometry.get("coordinates");
				JSONArray arreglo2 = (JSONArray) coordinates.get(0);
				//arreglo3 es el arreglo de duplas
				JSONArray arreglo3 = (JSONArray )arreglo2.get(0);
				Coordenada[] coordenadas = new Coordenada[arreglo3.size()];
				for(int j = 0; j < arreglo3.size(); j++)
				{
					//arreglo4 es cada dupla
					JSONArray arreglo4 = (JSONArray)arreglo3.get(j);
					coordenadas[j] = new Coordenada((double) arreglo4.get(0), (double) arreglo4.get(1));
				}

				JSONObject properties = (JSONObject ) elemento.get("properties");
				String movement_id = (String) properties.get("MOVEMENT_ID");
				String scanombre = (String) properties.get("scanombre");
				double shape_leng = (double) properties.get("shape_leng");
				double shape_area = (double) properties.get("shape_area");

				Zona zona = new Zona(movement_id, scanombre, shape_leng, shape_area,coordenadas);
				zonas.agregar(zona);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			reader.close();

		}


	}

	//----------------------------------------------
	// Métodos parte A
	//----------------------------------------------

	//faltan parametros (si los hay)
	public void letrasMasRepetidas()
	{
		
	}
	
	//faltan parametros (si los hay)
	public void buscarNodosA()
	{
		
	}
	
	//faltan parametros (si los hay)
	//se usa estos datos: viajesMonthly1
	public void tiempoPromedioA()
	{
		
	}

	//----------------------------------------------
	// Métodos parte B
	//----------------------------------------------

	/**
	 * Buscar las N zonas más al norte del mapa. 
	 * Una zona está más al norte que otra si la latitud de la zona A es mayor que la latitud de la zona B.
	 * @param N La cantidad de zonas que se quieren saber. N > 0
	 */
	public MaxHeapCP<Zona> zonasMasNorte(int N)
	{
		Nodo<Zona> tempo = zonas.darPrimero();
		MaxHeapCP<Zona> heapZona = new MaxHeapCP<Zona>(zonas.darTamano());
		for(int i = 0; i < heapZona.darElementosPosibles() && tempo != null; i++)
		{
			heapZona.agregar(tempo.darElemento());
			tempo = tempo.siguiente;
		}
		
		MaxHeapCP<Zona> heapZonaRespuesta = new MaxHeapCP<Zona>(N);
		for(int i = 0; i < N; i++)
		{
			heapZonaRespuesta.agregar(heapZona.sacarMax());
		}
		
		return heapZonaRespuesta;
	}
	
	/**
	 * Dado una latitud y una longitud, se deben mostrar todos los nodos que tengan 
	 * esas mismas latitud y longitud truncando a 2 cifras decimales.
	 * @param latitud
	 * @param longitud
	 * @return
	 */
	public TablaHashSeparateChaining<String, NodoBogota> buscarNodosB(double latitud, double longitud)
	{
		TablaHashSeparateChaining<String, NodoBogota> nodos = new TablaHashSeparateChaining<String, NodoBogota>(mapaBogota.darTamano());
		Nodo<NodoBogota> temporal = mapaBogota.darPrimero();
		
		while(temporal != null)
		{
			if(DoubleRounder.round(temporal.elemento.getLatitud(),2) == DoubleRounder.round(latitud,2)
			&& DoubleRounder.round(temporal.elemento.getLongitud(),2) == DoubleRounder.round(longitud,2))
				nodos.put(Integer.toString(temporal.elemento.getId()), temporal.elemento);
			temporal = temporal.siguiente;
		}
		return nodos;
	}
	
	/**
	 * Dado un rango de desviaciones estándares [limite_bajo, limite_alto] 
	 * retornar los viajes cuya desviación estándar mensual este en ese rango.
	 * @param limite_abajo
	 * @param limite_arriba
	 * @return
	 */
	public ArbolRojoNegro<TravelTime, Double> desviacionEstandarB(double limite_abajo, double limite_arriba)
	{
		ArbolRojoNegro<TravelTime, Double> arbol = new ArbolRojoNegro<TravelTime, Double>();
		Nodo<TravelTime> tempo = viajesMonthly1.darPrimero();
		
		while(tempo != null)
		{
			double desviacion = tempo.elemento.getStandard_deviation_travel_time();
			if(desviacion > limite_abajo && desviacion < limite_arriba)
				arbol.put(tempo.elemento, tempo.elemento.getStandard_deviation_travel_time());
			tempo = tempo.siguiente;
		}
		return arbol;
	}
	
	//----------------------------------------------
	// Métodos parte C
	//----------------------------------------------

	
	


	//----------------------------------------------
	// Getters
	//----------------------------------------------

	public int getTrimestre()
	{
		return trimestre;
	}

	public ListaDoblementeEncadenada<TravelTime> getViajesHourly1() {
		return viajesHourly1;
	}

	public ListaDoblementeEncadenada<TravelTime> getViajesHourly2() {
		return viajesHourly2;
	}

	public ListaDoblementeEncadenada<TravelTime> getViajesWeekly1() {
		return viajesWeekly1;
	}

	public ListaDoblementeEncadenada<TravelTime> getViajesWeekly2() {
		return viajesWeekly2;
	}

	public ListaDoblementeEncadenada<TravelTime> getViajesMonthly1() {
		return viajesMonthly1;
	}

	public ListaDoblementeEncadenada<TravelTime> getViajesMonthly2() {
		return viajesMonthly2;
	}
	public ListaDoblementeEncadenada<NodoBogota> getMapaBogota() {
		return mapaBogota;
	}
	public ListaDoblementeEncadenada<Zona> getZonas() {
		return zonas;
	}

}
