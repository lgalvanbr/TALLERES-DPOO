package uniandes.dpoo.hamburguesas.tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Producto;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;


public class ComboTest {
	
	public Combo combo1;
	public ArrayList<ProductoMenu> itemsCombo;
	public ProductoMenu gaseosa;
	public ProductoMenu papasFritas;
	public double descuento;
	@BeforeEach
	void setUp() {
		gaseosa= new ProductoMenu("gaseosa",2000);
		papasFritas= new ProductoMenu("Papas",3500);
		itemsCombo= new ArrayList<>();
		itemsCombo.add(gaseosa);
		itemsCombo.add(papasFritas);
		descuento=0.93;
		combo1= new Combo("megamiercoles", descuento, itemsCombo);
	}
	@Test
	void testGetNombre(){
		
		assertEquals("megamiercoles",combo1.getNombre(),"no funca");
		
		
	}
	@Test
	void testGetPrecio() {
		assertEquals(5115,combo1.getPrecio(),"no funca");
	}
	
	@Test
	void testGenerarTextoFactura() {
		assertEquals( "Combo megamiercoles\n Descuento: 0.93\n            5115\n",combo1.generarTextoFactura(),"nofunca");
	}
	

}
