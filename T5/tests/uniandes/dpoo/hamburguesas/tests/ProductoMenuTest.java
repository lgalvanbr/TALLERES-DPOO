package uniandes.dpoo.hamburguesas.tests;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoMenuTest{

	
	public ProductoMenu producto1;
	
	@BeforeEach
	public void setUp() throws Exception {
	producto1= new ProductoMenu("hamburguesa", 15000);
	}
	
	@Test
	public void testGetNombre() {
		
		assertEquals("hamburguesa",producto1.getNombre(),"el nombre no fue el esperado");
		
	}
	@Test
	public void testGetPrecio() {
		
		assertEquals(15000,producto1.getPrecio(),"el costo no fue el esperado");
		
	}
	//@AfterEach
	//public void setUp() {
		//textofact= new generartextoFactura()
	//}
	@Test
	public void testGenerarTextoFactura() {
		assertEquals("hamburguesa\n            15000\n",producto1.generarTextoFactura(),"no funciono");
		}
	
}
