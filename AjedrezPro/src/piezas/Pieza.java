
package piezas;

import ajedrezpro.Tablero;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Pieza {
    
    public BufferedImage imagen;
    public int x, y;
    public int col, fil, preCol, preFil;
    public int color;
    
    public Pieza(int color, int col, int fil){
        this.color = color;
        this.col = col;
        this.fil = fil;
        x = getX(col);
        y = getY(fil);
        preCol = col;
        preFil = fil;
    }

    public BufferedImage getImagen(String PathImagen) {
        BufferedImage imagen = null;
        try{
            imagen = ImageIO.read(getClass().getResourceAsStream(PathImagen + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imagen;
    }
    
    
    public int getX(int col){
        return col * Tablero.tamano_cuadro;
    }
    
    public int getY(int fil){
        return fil * Tablero.tamano_cuadro;
    }
    
    public int getCol(int x) {
        return (x + Tablero.mitad_cuadro/Tablero.tamano_cuadro);
    }
    
    public int getFil(int y) {
        return (y + Tablero.mitad_cuadro/Tablero.tamano_cuadro);
    }
    
    public void actualizarPosicion() {
        x = getX(col);
        y = getY(fil);
        preCol = getCol(x);
        preFil = getFil(y);
    }
    
    public void reiniciarPosicion() {
        col = preCol;
        fil = preFil;
        x = getX(col);
        y = getY(fil);
    }
    
    public boolean puedeMover(int targetCol, int targetFil) {
        return false;
    }
    
    public boolean estaEnElTablero(int targetCol, int targetFil) {
        if (targetCol >= 0 && targetCol <= 7 && targetFil >= 0 && targetFil <= 7) {
            return true;
        }
        return false;
    }
    
    public void dibujar(Graphics2D a2) {
        a2.drawImage(imagen, x, y, Tablero.tamano_cuadro, Tablero.tamano_cuadro, null);
    }
    
}
