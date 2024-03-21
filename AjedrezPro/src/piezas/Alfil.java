
package piezas;

import ajedrezpro.PanelAjedrez;

public class Alfil extends Pieza {
    
    public Alfil(int color, int col, int fil) {
        super(color, col, fil);
        
        if(color == PanelAjedrez.blanco){
            imagen = getImagen("/piezas/alfil");
        } else{
            imagen = getImagen("/piezas/alfilotro");
        }
    }
    
}
