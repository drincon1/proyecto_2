package view;

import java.util.Iterator;
import java.util.Scanner;

import model.data_structures.ArbolRojoNegro;
import model.data_structures.ListaDoblementeEncadenada;
import model.data_structures.MaxHeapCP;
import model.data_structures.Nodo;
import model.data_structures.TablaHashSeparateChaining;
import model.logic.NodoBogota;
import model.logic.TravelTime;
import model.logic.Zona;
import ordenamientos.QuickSort;

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

	public int scannerInt()
	{
		return lector.nextInt();
	}

	public double scannerDouble()
	{
		return lector.nextDouble();
	}
	public String scannerString()
	{
		return lector.next();
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

	public int printMenu()
	{
		printMensaje("\n¿Cuál opción desea?\n(-1 para salir)");

		printMensaje("4) Buscar los N zonas que están más al norte");
		printMensaje("5) Buscar nodos de la malla vial por Localización Geográfica (truncando a 2 decimales)");
		printMensaje("6) Buscar los tiempos de espera que tienen una desviación estándar en un rango dado y que son del primer trimestre del 2018.");
		int opcion = lector.nextInt();
		return opcion;
	}


	//----------------------------------------------
	// Impresores parte B
	//----------------------------------------------
	public void print1B(MaxHeapCP<Zona> heap)
	{
		Zona max = heap.sacarMax();
		int i = 1;
		while(max != null)
		{
			printMensaje(i + ") " + max.getScanombre() + "(" + max.getCoordenadaMasNorte().longitud + ", " + max.getCoordenadaMasNorte().latitud + ")");

			max = heap.sacarMax();
			i++;
		}
	}
	public void printBuscarNodosB(TablaHashSeparateChaining<String, NodoBogota> nodos)
	{
		printMensaje("Número de nodos encontrados: " + nodos.N);
		int i = 1;
		ListaDoblementeEncadenada<NodoBogota> valores = nodos.getValues();
		Nodo<NodoBogota> llave = valores.darPrimero(); 
		while(llave != null)
		{
			printMensaje(i + ") ID: "+ llave.elemento.getId() + "(" + llave.elemento.getLongitud() + "," + llave.elemento.getLatitud() + ")");
			i++;
			llave = llave.siguiente;
		}

	}

	public void printDesviacionEstandarB(ArbolRojoNegro<TravelTime, Double> arbol, int cantidad)
	{

		if(cantidad > arbol.size)
		{
			printMensaje("La cantidad es mayor que el número de resultados");
			cantidad = arbol.size;
		}
		printMensaje("Número de viajes en ese rango: " + arbol.size());
		Iterator<TravelTime> viajes = arbol.keys();
		TravelTime temp = viajes.next();
		TravelTime[] viajesArreglo = new TravelTime[cantidad];
		int i = 0;
		while(i < cantidad)
		{
			viajesArreglo[i] = temp;
			temp = viajes.next(); 
			i++;
		}
		QuickSort<TravelTime> quickSort = new QuickSort<TravelTime>(viajesArreglo);
		i = 0;
		while(i < cantidad)
		{
			printMensaje((i+1) + ") Zona origen: " + viajesArreglo[i].getSourceid() + " Zona destino: " + viajesArreglo[i].getDistid() + " Mes:" + viajesArreglo[i].getTiempo() + " Desviación estandar: " + viajesArreglo[i].getStandard_deviation_travel_time());
			i++;
		}
	}


}
