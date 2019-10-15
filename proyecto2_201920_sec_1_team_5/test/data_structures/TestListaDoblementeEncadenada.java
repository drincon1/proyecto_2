package data_structures;

import static org.junit.Assert.*;

import org.junit.*;

import model.data_structures.ListaDoblementeEncadenada;

public class TestListaDoblementeEncadenada 
{
	private ListaDoblementeEncadenada<String> lista;
	
	@Before
	public void setUp1()
	{
		lista = new ListaDoblementeEncadenada();
	}
	
	public void setUp2()
	{
		lista.agregar("1");
		lista.agregar("2");
		lista.agregar("3");
		lista.agregar("4");
	}
	
	@Test
	public void testListaNueva()
	{
		assertTrue(lista.isEmpty());
		assertEquals(0, lista.darTamano());
	}
	
	@Test
	public void testListaLlena()
	{
		setUp2();
		assertEquals(4, lista.darTamano());
		assertEquals("1", lista.darPrimero().darElemento());
		assertEquals("4", lista.darUltimo().darElemento());
		assertEquals("2", lista.darPrimero().darSiguiente().darElemento());	
	}
	
	public void testEliminar()
	{
		setUp2();
		lista.eliminar("4");
		assertEquals("3", lista.darUltimo());
		assertEquals(3, lista.darTamano());
		lista.agregar("4");
		lista.eliminar("2");
		assertEquals("3", lista.darPrimero().darSiguiente());	
	}
	
}
