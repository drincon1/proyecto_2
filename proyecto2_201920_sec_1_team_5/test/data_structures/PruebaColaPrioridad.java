package data_structures;
import static org.junit.Assert.*;

import model.data_structures.*;
import model.logic.*;
import model.logic.TravelTime.TipoTiempo;

import org.junit.Before;
import org.junit.Test;

public class PruebaColaPrioridad 
{	
	//------------------------------------
	// Pruebas a MaxColaCP
	//------------------------------------
	
	private MaxColaCP<TravelTime> emptyQueue;
	private MaxColaCP<TravelTime> filledQueue;
	
	TravelTime tempo;
	
	@Before
	public void setUp2() {
		emptyQueue = new MaxColaCP<TravelTime>();
		filledQueue = new MaxColaCP<TravelTime>();
		int inc = 0;
		for (int i = 0; i < 20; i++) {
			inc *= 3;
			int rd = (int) Math.random() * inc; 
			filledQueue.agregar(new TravelTime(2, rd, rd, rd, rd, rd, TipoTiempo.DIA));
		}
		tempo = new TravelTime(2, 600, 250, 12, 10895.6, 423425.3, TipoTiempo.DIA);
		filledQueue.agregar(tempo);
	}

	//------------------------
	// Pruebas a MaxColaCP
	//------------------------
	
	public void testEmptyQueue () {
		assertEquals (0, emptyQueue.darNumElementos());
		assertEquals(null, emptyQueue.darMax());
		emptyQueue.agregar(tempo);
		assertEquals(tempo, emptyQueue.darMax());
		assertEquals (1, emptyQueue.darNumElementos());
		
	}
	
	public void testFilledQueue () {
		for (int alpha = 0; alpha < filledQueue.darNumElementos(); alpha++) {
			System.out.println(filledQueue.darMax().getMean_travel_time());
		}
		
		assertEquals (20, filledQueue.darNumElementos());
	}
}
