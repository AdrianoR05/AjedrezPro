
package piezas;

import ajedrezpro.PanelAjedrez;

public class Reina extends Pieza {
    
    public Reina(int color, int col, int fil) {
        super(color, col, fil);
        
        if(color == PanelAjedrez.blanco){
            imagen = getImagen("/piezas/reina");
        } else{
            imagen = getImagen("/piezas/reinaotra");
        }       
    }
    
}
