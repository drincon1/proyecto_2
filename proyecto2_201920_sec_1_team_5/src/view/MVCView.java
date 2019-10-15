package view;

import java.util.Scanner;

import model.data_structures.ListaDoblementeEncadenada;
import model.logic.NodoBogota;
import model.logic.TravelTime;
import model.logic.Zona;

public class MVCView 
{
	private Scanner lector;

	public MVCView()
	{
		lector = new Scanner(System.in);
	}

	public void printMensaje(String mensaje)
	{
		System.out.println(mensaje);
	}

	public int printCargar()
	{
		printMensaje("¿Cuál trimestre quiere prioritar?\n"
				+ "Tengan en cuenta que las operaciones se hace sobre los primeros 6 meses del año");
		int trimestre = lector.nextInt();
		while(trimestre > 3)
		{
			printMensaje("Valor no aceptado");
			trimestre = lector.nextInt();
		}
		return trimestre;
	}

	public void printRespuestaCargar(ListaDoblementeEncadenada<TravelTime>...viajes)
	{
		for(int i = 0; i < viajes.length; i++)
		{
			if(viajes[i].darPrimero() != null)
			{
				String tipo = viajes[i].darPrimero().darElemento().getTipoTiempo().toString();
				switch(tipo)
				{
				case "HORA": printMensaje("Número de viajes del trimestre " + viajes[i].darPrimero().darElemento().getTrimestre() + " [horas]: " + viajes[i].darTamano());
				break;
				case "DIA": printMensaje("Número de viajes del trimestre "+ viajes[i].darPrimero().darElemento().getTrimestre() + " [días]: "  + viajes[i].darTamano());
				break;
				default:  printMensaje("Número de viajes del trimestre " + viajes[i].darPrimero().darElemento().getTrimestre() + " [meses]: "  + viajes[i].darTamano());
				break;

				}	
			}
		}
	}

	public void printInfoMapa(ListaDoblementeEncadenada<NodoBogota> mapaBogota)
	{
		printMensaje("La cantidad de nodos cargados fueron: " + mapaBogota.darTamano());
		printMensaje("El primer nodo del mapa de Bogotá es: (" + mapaBogota.darPrimero().elemento.getLongitud() + ", " + mapaBogota.darPrimero().elemento.getLatitud() + ")");
		printMensaje("El último nodo del mapa de Bogotá es: (" + mapaBogota.darUltimo().elemento.getLongitud() + ", " + mapaBogota.darUltimo().elemento.getLatitud() + ")");
	}

	public void printInfoZonas(ListaDoblementeEncadenada<Zona> zonas)
	{
		printMensaje("La cantidad de zonas cargads fueron: " + zonas.darTamano());
		printMensaje("La primera zona tiene nombre: " + zonas.darPrimero().elemento.getScanombre());
		printMensaje("La última zona tiene nombre: " + zonas.darUltimo().elemento.getScanombre());

	}
}
