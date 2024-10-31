package uniandes.dpoo.hamburguesas.tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Producto;
import uniandes.dpoo.hamburguesas.mundo.Pedido;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import static org.junit.jupiter.api.Assertions.*;


public class PedidoTest {
	
	public Pedido pedido1;
	public String nombreCliente;
	public String direccionCliente;
	public ArrayList<ProductoMenu> productos;
	public ProductoMenu gaseosa;
	public ProductoMenu papasFritas;
	public int i=2;
	@BeforeEach
	void setUp() {
		pedido1=new Pedido("lucho","calle21");
		gaseosa= new ProductoMenu("gaseosa",2000);
		papasFritas= new ProductoMenu("Papas",3500);
		Producto producto = new ProductoMenu("ProductoPrueba", 100);
	}
	
	@Test
	void testGetidpedido() {
		i++;
		assertEquals(i,pedido1.getIdPedido(),"nanai");
		
	}
	@Test
	void testGetNombreCliente() {
		assertEquals("lucho",pedido1.getNombreCliente(),"nanai");
		
	}
	@Test
	void testAgregarProducto() {
	    int precioInicial = pedido1.getPrecioTotalPedido(); // Precio inicial del pedido

	    // Agregar un producto al pedido
	    pedido1.agregarProducto(gaseosa);

	    // Verificar que el precio total cambie después de agregar el producto
	    int precioEsperado = precioInicial + gaseosa.getPrecio() + (int) (gaseosa.getPrecio() * 0.19); // Con IVA
	    assertEquals(precioEsperado, pedido1.getPrecioTotalPedido(), "El producto no fue agregado correctamente al pedido");
	}

	
	@Test
	void testgenerarTextoFactura() {
	    String expected = "Cliente: lucho" + System.lineSeparator()
	                    + "Dirección: calle21" + System.lineSeparator()
	                    + "----------------" + System.lineSeparator()
	                    + "----------------" + System.lineSeparator()
	                    + "Precio Neto:  0" + System.lineSeparator()
	                    + "IVA:          0" + System.lineSeparator()
	                    + "Precio Total: 0";

	    String actual = pedido1.generarTextoFactura().strip();

	    // Elimina espacios en blanco adicionales y normaliza el formato de ambos textos
	    String normalizedExpected = expected.replaceAll("\\s+", "");
	    String normalizedActual = actual.replaceAll("\\s+", "");

	    assertEquals(normalizedExpected, normalizedActual, "El texto de la factura no coincide con el esperado");
	}
    //void testGuardarFactura() throws Exception {
	@Test
    void testGuardarFactura() throws IOException {
        File archivoTemporal = File.createTempFile("factura", ".txt");
        archivoTemporal.deleteOnExit();

        // Agregar productos al pedido para que la factura tenga contenido
        pedido1.agregarProducto(gaseosa);
        pedido1.agregarProducto(papasFritas);

        // Guardar la factura en el archivo temporal
        pedido1.guardarFactura(archivoTemporal);

        // Leer el contenido del archivo
        String contenidoArchivo = Files.readString(archivoTemporal.toPath());

        // Verificar que el contenido del archivo coincida con la factura generada
        assertEquals(pedido1.generarTextoFactura(), contenidoArchivo, "El contenido de la factura no coincide");
    }
}

	

