package Launcher;

import java.awt.EventQueue;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Ventana ventana = new Ventana();
                PanelCarga carga = new PanelCarga(ventana); // Panel con barra de carga
                ventana.mostrarPanel(carga, "carga");
                ventana.setVisible(true);
            }
        });
    }
}
