
package ajedrezpro;

import javax.swing.JFrame;

public class AjedrezPro {

    public static void main(String[] args) {
        
        JFrame ventana = new JFrame("Ajedrez");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        
        //Agregar panel de juego a la ventana
        PanelAjedrez panel = new PanelAjedrez();
        ventana.add(panel);
        ventana.pack();
        
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        
        panel.correrJuego();
    }
    
}
