package main;

import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;

import model.data_structures.ListaDoblementeEncadenada;
import model.logic.TravelTime;
import model.logic.TravelTime.TipoTiempo;

public class MainPrueba {

	static ListaDoblementeEncadenada<TravelTime> viajesHourly2;
	public static void main(String[] args)
	{
		viajesHourly2 = new ListaDoblementeEncadenada<TravelTime>();
		try
		{
			cargarHora2();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		System.out.println(viajesHourly2.darTamano());



	}

	public static void cargarHora2() throws IOException
	{
		CSVReader reader = null;
		double duration = -1;
		try
		{
			String ruta =  "./data/bogota-cadastral-2018-2-All-MonthlyAggregate.csv";
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

}
