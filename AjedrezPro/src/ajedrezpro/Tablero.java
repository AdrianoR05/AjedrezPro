
package ajedrezpro;

import java.awt.Color;
import java.awt.Graphics2D;

public class Tablero {
    
    final int maxCol = 8;
    final int maxFil = 8;
    public static final int tamano_cuadro = 100;
    public static final int mitad_cuadro = tamano_cuadro/2;
    
    public void draw(Graphics2D a2) {
        
        int c = 0;
        
        for(int fil = 0; fil < maxFil; fil++) {
            
            for(int col = 0; col < maxCol; col++) {
                
                if(c == 0) {
                    a2.setColor(new Color(0, 0, 0));
                    c = 1;
                }
                else {
                    a2.setColor(new Color(128, 128, 128));
                    c = 0;
                }
                a2.fillRect(col*tamano_cuadro, fil*tamano_cuadro, tamano_cuadro, tamano_cuadro);
            }
            
            if(c == 0) {
                c = 1;
            }
            else {
                c = 0;
            }
        }
    }
    
}

