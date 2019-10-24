package controller;

import java.io.IOException;
import java.util.Iterator;

import model.data_structures.ArbolRojoNegro;
import model.data_structures.TablaHashSeparateChaining;
import model.logic.MVCModelo;
import model.logic.NodoBogota;
import model.logic.TravelTime;
import view.MVCView;

public class Controller 
{
	MVCModelo modelo;
	MVCView view;

	public Controller()
	{
		modelo = new MVCModelo();
		view  = new MVCView();
	}

	public void run() throws IOException
	{
		modelo.cargar(view.printCargar());
		view.printRespuestaCargar(modelo.getViajesHourly1(), modelo.getViajesWeekly1(), modelo.getViajesMonthly1(),modelo.getViajesHourly2(),modelo.getViajesWeekly2(),modelo.getViajesMonthly2());
		view.printMensaje("*********************************");

		long startTime = System.currentTimeMillis();
		modelo.cargarMapa();
		long endTime = System.currentTimeMillis();
		long duration = endTime - startTime;
		view.printInfoMapa(modelo.getMapaBogota());
		view.printMensaje("Cargar el mapa se demoró: " + duration + " milisegundos (" + (duration /1000.0) + " segundos)");
		view.printMensaje("*********************************");

		startTime = System.currentTimeMillis();
		modelo.cargarJSON();
		endTime = System.currentTimeMillis();
		view.printInfoZonas(modelo.getZonas());
		duration = endTime - startTime;
		view.printMensaje("Cargar las zonas se demoró: " + duration + " milisegundos (" + (duration /1000.0) + " segundos)");
		view.printMensaje("*********************************");


		int opcion = view.printMenu();
		while(opcion > 0)
		{
			switch(opcion)
			{
			case 1: modelo.letrasMasRepetidas();
				break;
			case 2: modelo.buscarNodosA();
				break;
			case 3: modelo.tiempoPromedioA();
				break;
			case 4: 
				view.printMensaje("¿Cuantas zonas quiere ver?");
				view.print1B(modelo.zonasMasNorte(view.scannerInt()));
				break;
			case 5: 
				view.printMensaje("Latitud");
				double latitud = Double.parseDouble(view.scannerString());
				view.printMensaje("Longitud");
				double longitud = Double.parseDouble(view.scannerString());
				view.printBuscarNodosB(modelo.buscarNodosB(latitud, longitud));
				break;
			case 6:
				view.printMensaje("Limite por abajo");
				double limite_abajo = Double.parseDouble(view.scannerString());
				view.printMensaje("Limite por arriba");
				double limite_arriba = Double.parseDouble(view.scannerString());
				view.printMensaje("¿Cuantos viajes quiere ver?");
				int cantidad = view.scannerInt();
				view.printDesviacionEstandarB(modelo.desviacionEstandarB(limite_abajo, limite_arriba), cantidad);
				break;
			case 7:
				break;
			case 8:
				break;
			case 9:
				break;
			case 10:
				break;
			}
			opcion = view.printMenu();
		}

	}


}
