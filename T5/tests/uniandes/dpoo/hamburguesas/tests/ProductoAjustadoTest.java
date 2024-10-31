package uniandes.dpoo.hamburguesas.tests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ProductoAjustadoTest {

    private ProductoMenu productoBase;
    private ProductoAjustado productoAjustado;
    private Ingrediente queso;
    private Ingrediente tomate;
	@BeforeEach
	void setUp() {
		
		productoBase= new ProductoMenu("hamburgesa",5000);
		productoAjustado= new ProductoAjustado(productoBase);
		queso= new Ingrediente("Queso",300);
		tomate= new Ingrediente("tomate",600);
		
	}
	@Test
	void testGetnombre() {
		assertEquals("hamburgesa",productoAjustado.getNombre(),"no funca");
	}
	@Test
	void testGetprecio() {
		assertEquals(5000,productoAjustado.getPrecio(),"no funca");
	}
	@Test
	void testGenerarTextoFactura() throws Exception  {
			Field agregadosField = ProductoAjustado.class.getDeclaredField("agregados");
		    agregadosField.setAccessible(true);
		    ArrayList<Ingrediente> agregados = (ArrayList<Ingrediente>) agregadosField.get(productoAjustado);

		    // Agregar un ingrediente
		    agregados.add(new Ingrediente("Queso", 300));

		    // Acceder y modificar la lista de ingredientes eliminados mediante reflexión
		    Field eliminadosField = ProductoAjustado.class.getDeclaredField("eliminados");
		    eliminadosField.setAccessible(true);
		    ArrayList<Ingrediente> eliminados = (ArrayList<Ingrediente>) eliminadosField.get(productoAjustado);

		    // Eliminar un ingrediente
		    eliminados.add(new Ingrediente("Cebolla", 0));

		    // Construir el texto de factura esperado
		    String facturaEsperada = "hamburgesa\n" +
		                             "    +Queso                300\n" +
		                             "    -Cebolla\n" +
		                             "            5300\n";

		    assertEquals(facturaEsperada, productoAjustado.generarTextoFactura(), "La factura no se generó correctamente.");
		}
	
	
}

