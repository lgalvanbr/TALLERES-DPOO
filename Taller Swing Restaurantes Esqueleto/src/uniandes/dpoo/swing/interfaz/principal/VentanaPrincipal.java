package uniandes.dpoo.swing.interfaz.principal;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;

import uniandes.dpoo.swing.interfaz.agregar.VentanaAgregarRestaurante;
import uniandes.dpoo.swing.interfaz.mapa.VentanaMapa;
import uniandes.dpoo.swing.mundo.Diario;
import uniandes.dpoo.swing.mundo.Restaurante;

@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame {
    /**
     * Referencia al diario que contiene los restaurantes
     */
    private Diario mundo;

    /**
     * El panel con los botones para crear un restaurante o ver el mapa
     */
    private PanelBotones pBotones;

    /**
     * El panel que muestra los detalles del restaurante seleccionado
     */
    private PanelDetallesRestaurante pDetalles;

    /**
     * El panel que contiene la lista de restaurantes
     */
    private PanelLista pLista;

    /**
     * Una referencia a la ventana del mapa, si ya se abrió alguna vez
     */
    private VentanaMapa ventanaMapa;

    /**
     * Una referencia a la ventana donde se agregan restaurantes, si ya se abrió alguna vez
     */
    private VentanaAgregarRestaurante ventanaAgregar;

    public VentanaPrincipal(Diario elDiario) {
        this.mundo = elDiario;
        setLayout(new BorderLayout());

        // Configura los componentes de la ventana
        pBotones = new PanelBotones(this);
        add(pBotones, BorderLayout.NORTH);

        pLista = new PanelLista(this);
        add(pLista, BorderLayout.CENTER);

        pDetalles = new PanelDetallesRestaurante();
        add(pDetalles, BorderLayout.SOUTH);

        // Actualiza la lista de restaurantes
        actualizarRestaurantes();

        // Configura la ventana
        setTitle("Gestión de Restaurantes");
        setSize(400, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Abre la ventana para agregar un nuevo restaurante, si no está abierta
     */
    public void mostrarVetanaNuevoRestaurante() {
        if (ventanaAgregar == null || !ventanaAgregar.isVisible()) {
            ventanaAgregar = new VentanaAgregarRestaurante(this);
            ventanaAgregar.setVisible(true);
        }
    }

    /**
     * Abre la ventana del mapa para mostrar los restaurantes, si no está abierta
     */
    public void mostrarVentanaMapa() {
        if (ventanaMapa == null || !ventanaMapa.isVisible()) {
            ventanaMapa = new VentanaMapa(this, mundo.getRestaurantes(true));
            ventanaMapa.setVisible(true);
        }
    }

    /**
     * Agrega un nuevo restaurante al diario y actualiza la lista
     */
    public void agregarRestaurante(String nombre, int calificacion, int x, int y, boolean visitado) {
        Restaurante nuevo = new Restaurante(nombre, calificacion, x, y, visitado);
        mundo.agregarRestaurante(nuevo);
        actualizarRestaurantes();
    }

    /**
     * Actualiza la lista de restaurantes mostrada en la interfaz
     */
    private void actualizarRestaurantes() {
        List<Restaurante> todos = mundo.getRestaurantes(true);
        pLista.actualizarRestaurantes(todos);
        if (!todos.isEmpty()) {
            cambiarRestauranteSeleccionado(todos.get(0));
        }
    }

    /**
     * Cambia el restaurante seleccionado y actualiza los detalles mostrados
     */
    public void cambiarRestauranteSeleccionado(Restaurante seleccionado) {
        pDetalles.actualizarRestaurante(seleccionado);
    }
    
    /**
     * Retorna una lista de los restaurantes.
     * 
     * Si se quieren todos los restaurantes, 'completos' debe ser verdadero. De lo contrario, se retornan sólo los visitados.
     * @param completos Indica si se quieren todos los restaurantes o solo los ya visitados.
     * @return Una lista de restaurantes.
     */
    public List<Restaurante> getRestaurantes(boolean completos) {
        return mundo.getRestaurantes(completos);
    }


    /**
     * Método principal para iniciar la aplicación
     */
    public static void main(String[] args) {
        Diario elDiario = new Diario();
        elDiario.agregarRestaurante(new Restaurante("Pita Pan", 4, 30, 30, true));
        elDiario.agregarRestaurante(new Restaurante("Lord of the Wings", 5, 170, 210, true));
        elDiario.agregarRestaurante(new Restaurante("Nacho Business", 2, 350, 170, false));
        elDiario.agregarRestaurante(new Restaurante("Thai Tanic", 1, 110, 100, false));
        elDiario.agregarRestaurante(new Restaurante("Planet of the Creppes", 3, 400, 400, true));

        new VentanaPrincipal(elDiario);
    }
}
