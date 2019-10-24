package data_structures;
import static org.junit.Assert.*;

import org.decimal4j.util.DoubleRounder;
import org.junit.Before;
import org.junit.Test;

import model.data_structures.MaxHeapCP;

public class TestColaPrioridad 
{
	MaxHeapCP<String> heap;
	@Before
	public void setUp()
	{
		heap = new MaxHeapCP<String>(5);
		heap.agregar("c");
		heap.agregar("b");
		heap.agregar("e");
		heap.agregar("d");
		heap.agregar("a");
		
		System.out.println(DoubleRounder.round(.12345,2) == .12);
	}
	
	@Test
	public void testColaPrioridad()
	{
		assertFalse(heap.esVacia());
		assertEquals("e", heap.darMax());
		heap.sacarMax();
		assertEquals("d", heap.darMax());
		
	}
}
