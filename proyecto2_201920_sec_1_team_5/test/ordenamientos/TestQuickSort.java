package ordenamientos;
import static org.junit.Assert.*;

import org.junit.*;

import ordenamientos.QuickSort;
public class TestQuickSort 
{



	private String[] lista;
	private QuickSort<String> quickSort;

	@Before
	public void setUp1()
	{
		lista = new String[5];
		lista[0] = "d";
		lista[1] = "b";
		lista[2] = "e";
		lista[3] = "d";
		lista[4] = "c";

		quickSort = new QuickSort<String>(lista);
	}

	/**
	 * Caso en que la lista esta ordenada
	 */
	public void setUp2()
	{
		lista = new String[5];
		lista[0] = "a";
		lista[1] = "b";
		lista[2] = "c";
		lista[3] = "d";
		lista[4] = "e";
	}

	/**
	 * Caso: datos llegan en orden descendente
	 */
	public void setUp3()
	{
		lista = new String[5];
		lista[4] = "a";
		lista[3] = "b";
		lista[2] = "c";
		lista[1] = "d";
		lista[0] = "e";
		quickSort = new QuickSort<String>(lista);
	}

	@Test
	public void quickSortDesordenado()
	{
		assertTrue(quickSort.isSorted(lista));
	}

	@Test
	public void quickSortOrdenado()
	{
		setUp2();
		quickSort = new QuickSort<String>(lista);
		assertTrue(quickSort.isSorted(lista));
	}

	@Test
	public void quickSortDescendente()
	{
		setUp3();
		assertTrue(quickSort.isSorted(lista));
	}
}

