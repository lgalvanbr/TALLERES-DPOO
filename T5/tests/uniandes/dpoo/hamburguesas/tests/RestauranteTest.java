package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RestauranteTest {

    public static final String CARPETA_FACTURAS = null;
	private Restaurante restaurante;

    @BeforeEach
    public void setUp() {
        // Inicializa una nueva instancia de Restaurante para cada prueba
        restaurante = new Restaurante();
    }

    @Test
    public void testIniciarPedido() throws YaHayUnPedidoEnCursoException {
        // Prueba que se pueda iniciar un nuevo pedido
        restaurante.iniciarPedido("Cliente 1", "Calle 123");
        Pedido pedido = restaurante.getPedidoEnCurso();
        assertNotNull(pedido, "Debería haber un pedido en curso.");
        assertEquals("Cliente 1", pedido.getNombreCliente(), "El nombre del cliente no coincide.");
    }

    @Test
    public void testIniciarPedidoConPedidoEnCurso() throws YaHayUnPedidoEnCursoException {
        // Prueba que no se pueda iniciar un segundo pedido si ya hay uno en curso
        restaurante.iniciarPedido("Cliente 1", "Calle 123");
        assertThrows(YaHayUnPedidoEnCursoException.class, () -> {
        restaurante.iniciarPedido("Cliente 2", "Calle 456");
        }, "Debería lanzar una excepción cuando ya hay un pedido en curso.");
    }


    @Test
    public void testCerrarYGuardarPedido() throws YaHayUnPedidoEnCursoException, NoHayPedidoEnCursoException, IOException {
        // Asegura que la carpeta de facturas exista
        File carpetaFacturas = new File(Restaurante.CARPETA_FACTURAS);
        if (!carpetaFacturas.exists()) {
            carpetaFacturas.mkdirs(); // Crea la carpeta si no existe
        }

        // Inicia un pedido
        restaurante.iniciarPedido("Cliente 1", "Calle 123");

        // Simula cerrar y guardar el pedido
        restaurante.cerrarYGuardarPedido();

        // Verifica que no haya más pedidos en curso
        assertNull(restaurante.getPedidoEnCurso(), "No debería haber un pedido en curso después de cerrarlo.");

        // Verifica que el archivo se haya guardado
        File archivoFactura = new File(Restaurante.CARPETA_FACTURAS + "factura_0.txt");
        assertFalse(archivoFactura.exists(), "La factura debería haber sido guardada.");
    }


    @Test
    public void testCerrarYGuardarPedidoSinPedidoEnCurso() {
        // Prueba que no se pueda cerrar un pedido si no hay uno en curso
        assertThrows(NoHayPedidoEnCursoException.class, () -> {
            restaurante.cerrarYGuardarPedido();
        }, "Debería lanzar una excepción si no hay un pedido en curso.");
    }


    @Test
    public void testGetPedidoEnCurso() throws YaHayUnPedidoEnCursoException {
        // Prueba que getPedidoEnCurso devuelva el pedido en curso
        restaurante.iniciarPedido("Cliente 1", "Calle 123");
        Pedido pedido = restaurante.getPedidoEnCurso();
        assertNotNull(pedido, "Debería haber un pedido en curso.");
    }

    @Test
    public void testGetPedidos() {
        // Prueba que la lista de pedidos cerrados esté vacía al principio
        ArrayList<Pedido> pedidos = restaurante.getPedidos();
        assertTrue(pedidos.isEmpty(), "La lista de pedidos cerrados debería estar vacía inicialmente.");
    }

    @Test
    public void testCargarInformacionRestaurante() throws IOException {
        // Simula cargar la información de los archivos de ingredientes, menú y combos
        File archivoIngredientes = new File("data/ingredientes.txt");
        File archivoMenu = new File("data/menu.txt");
        File archivoCombos = new File("data/combos.txt");

        try {
            restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);
        } catch (Exception e) {
            fail("No debería lanzar ninguna excepción al cargar los archivos: " + e.getMessage());
        }

        // Verifica que la información se haya cargado correctamente
        assertFalse(restaurante.getIngredientes().isEmpty(), "La lista de ingredientes no debería estar vacía después de cargarla.");
        assertFalse(restaurante.getMenuBase().isEmpty(), "El menú base no debería estar vacío después de cargarlo.");
        assertFalse(restaurante.getMenuCombos().isEmpty(), "La lista de combos no debería estar vacía después de cargarla.");
    }
}


