package uniandes.dpoo.swing.interfaz.principal;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import uniandes.dpoo.swing.mundo.Restaurante;

@SuppressWarnings("serial")
public class PanelDetallesRestaurante extends JPanel {
    /**
     * La etiqueta donde se muestra el nombre de un restaurante
     */
    private JLabel labNombre;

    /**
     * La etiqueta donde se muestra la calificación de un restaurante, usando imágenes de estrellas
     */
    private JLabel labCalificacion;

    /**
     * Un checkbox en el que se muestra si un restaurante fue visitado o no
     */
    private JCheckBox chkVisitado;

    public PanelDetallesRestaurante() {
        // Configura el layout
        setLayout(new GridLayout(3, 2, 5, 5));

        // Etiqueta para mostrar el nombre del restaurante
        JLabel lblNombre = new JLabel("Nombre:");
        labNombre = new JLabel("No seleccionado");
        add(lblNombre);
        add(labNombre);

        // Etiqueta para mostrar la calificación
        JLabel lblCalificacion = new JLabel("Calificación:");
        labCalificacion = new JLabel();
        labCalificacion.setIcon(buscarIconoCalificacion(0)); // Default icon
        add(lblCalificacion);
        add(labCalificacion);

        // Checkbox para indicar si fue visitado
        JLabel lblVisitado = new JLabel("Visitado:");
        chkVisitado = new JCheckBox();
        chkVisitado.setEnabled(false); // Read-only checkbox
        add(lblVisitado);
        add(chkVisitado);
    }

    /**
     * Actualiza los datos mostrados del restaurante, indicando los valores por separado.
     * 
     * @param nombre       El nombre del restaurante.
     * @param calificacion La calificación del restaurante.
     * @param visitado     Si el restaurante fue visitado o no.
     */
    private void actualizarRestaurante(String nombre, int calificacion, boolean visitado) {
        labNombre.setText(nombre);
        labCalificacion.setIcon(buscarIconoCalificacion(calificacion));
        chkVisitado.setSelected(visitado);
    }

    /**
     * Actualiza los datos que se muestran de un restaurante.
     * 
     * @param r El restaurante que se debe mostrar.
     */
    public void actualizarRestaurante(Restaurante r) {
        if (r != null) {
            this.actualizarRestaurante(r.getNombre(), r.getCalificacion(), r.isVisitado());
        } else {
            this.actualizarRestaurante("No seleccionado", 0, false);
        }
    }
 
    /**
     * Dada una calificación, retorna una imagen para utilizar en la etiqueta que muestra la calificación.
     * 
     * @param calificacion La calificación del restaurante, que debe ser un número entre 1 y 5.
     * @return Una imagen que corresponde a la calificación.
     */
    private ImageIcon buscarIconoCalificacion(int calificacion) {
        String imagen = "./imagenes/stars" + calificacion + ".png";
        return new ImageIcon(imagen);
    }
}
