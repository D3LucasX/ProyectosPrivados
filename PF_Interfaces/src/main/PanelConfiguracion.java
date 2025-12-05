package main;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

public class PanelConfiguracion  {

	public JPanel panelConfiguracion;
    public JList<String> listaFuentesInternacionales;
    public JList<String> listaFuentesNacionales;
    public JLabel lblInternacional;
    public JLabel lblNacional;

    public PanelConfiguracion() {
        panelConfiguracion = new JPanel();
        panelConfiguracion.setLayout(null);

        // Etiqueta INTERNACIONAL
        lblInternacional = new JLabel("Categoría : INTERNACIONAL");
        lblInternacional.setHorizontalAlignment(SwingConstants.CENTER);
        lblInternacional.setBounds(105, 42, 146, 25);
        panelConfiguracion.add(lblInternacional);

        // Lista INTERNACIONALES
        listaFuentesInternacionales = new JList<>();
        listaFuentesInternacionales.setFont(new Font("Consolas", Font.PLAIN, 11));
        listaFuentesInternacionales.setBounds(139, 78, 79, 56);
        panelConfiguracion.add(listaFuentesInternacionales);
        String[] fuentesInternacionales = {"El Mundo", "El Pais", "BBC"};
        listaFuentesInternacionales.setListData(fuentesInternacionales);
        listaFuentesInternacionales.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // Etiqueta NACIONALES
        lblNacional = new JLabel("Categoría :  NACIONAL");
        lblNacional.setHorizontalAlignment(SwingConstants.CENTER);
        lblNacional.setBounds(261, 42, 146, 25);
        panelConfiguracion.add(lblNacional);

        // Lista NACIONALES
        listaFuentesNacionales = new JList<>();
        listaFuentesNacionales.setBounds(287, 76, 79, 56);
        panelConfiguracion.add(listaFuentesNacionales);
        String[] fuentesNacionales = {"El Mundo", "El Pais", "EL Nacional"};
        listaFuentesNacionales.setListData(fuentesNacionales);
        listaFuentesNacionales.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }
    
    
}
