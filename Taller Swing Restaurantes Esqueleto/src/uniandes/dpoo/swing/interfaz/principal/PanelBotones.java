package uniandes.dpoo.swing.interfaz.principal;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelBotones extends JPanel implements ActionListener {
    /**
     * El comando para el botón para crear un nuevo restaurante
     */
    private static final String NUEVO = "nuevo";

    /**
     * El comando para el botón para ver todos los restaurantes en el mapa
     */
    private static final String VER = "ver";

    private JButton butNuevo;
    private JButton butVerTodos;

    /**
     * La referencia a la ventana principal
     */
    private VentanaPrincipal ventanaPrincipal;

    public PanelBotones(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;

        // Configura el layout
        setLayout(new FlowLayout());

        // Agrega el botón para crear un nuevo restaurante
        butNuevo = new JButton("Nuevo Restaurante");
        butNuevo.setActionCommand(NUEVO);
        butNuevo.addActionListener(this);
        add(butNuevo);

        // Agrega el botón para ver todos los restaurantes
        butVerTodos = new JButton("Ver Todos");
        butVerTodos.setActionCommand(VER);
        butVerTodos.addActionListener(this);
        add(butVerTodos);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        if (comando.equals(NUEVO)) {
            // Llama a la ventana principal para mostrar la ventana de agregar restaurante
            ventanaPrincipal.mostrarVetanaNuevoRestaurante();
        } else if (comando.equals(VER)) {
            // Llama a la ventana principal para mostrar la ventana del mapa
            ventanaPrincipal.mostrarVentanaMapa();
        }
    }
}
