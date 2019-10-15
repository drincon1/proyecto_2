package controller;

import java.io.IOException;

import model.logic.MVCModelo;
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
	}
}
