
package piezas;

import ajedrezpro.PanelAjedrez;

public class Rey extends Pieza {
    
    public Rey(int color, int col, int fil) {
        super(color, col, fil);
        
        if(color == PanelAjedrez.blanco){
            imagen = getImagen("/piezas/rey");
        } else{
            imagen = getImagen("/piezas/reyotro");
        }
    }
    
    public boolean puedeMover(int targetCol, int targetFil){
        
        if (estaEnElTablero(targetCol,targetFil)){
            
            if(Math.abs(targetCol - preCol) + Math.abs(targetFil - preFil) == 1 || 
               Math.abs(targetCol - preCol) * Math.abs(targetFil - preFil) == 1) {
                return true;
            }
            
        }
        
        return false;
    }
    
}
