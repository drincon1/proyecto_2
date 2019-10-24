package data_structures;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.ArbolRojoNegro;

public class TestArbolRojoNegro 
{
	ArbolRojoNegro<String, String> arbol;
	@Before
	public void setUp()
	{
		arbol = new ArbolRojoNegro<String, String>();
		arbol.put("f", "seis");
		arbol.put("b", "dos");
		arbol.put("d", "cuatro");
		arbol.put("c", "tres");
		arbol.put("a", "uno");
		arbol.put("e", "cinco");
		System.out.println(arbol.size());

		
	}
	
	@Test
	public void testARN()
	{
		assertTrue(6 == arbol.size());
	}


	
	
}
