
package ajedrezpro;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import piezas.Alfil;
import piezas.Caballo;
import piezas.Peon;
import piezas.Pieza;
import piezas.Reina;
import piezas.Rey;
import piezas.Torre;

public final class PanelAjedrez extends JPanel implements Runnable{
    
    public static int ancho = 1360;
    public static int alto = 768;
    final int FPS = 60;
    Thread EjecutarThread;
    Tablero tablero = new Tablero();
    Mouse mouse = new Mouse();
    
    public static ArrayList<Pieza> piezas = new ArrayList<>();
    public static ArrayList<Pieza> simPiezas = new ArrayList<>();
    Pieza activarP;
    
    
    //color
    public static final int blanco = 0;
    public static final int negro = 1;
    int colorActual = blanco;
    
    //Booleanos
    boolean puedeMover;
    boolean validarCuadro;
    
    public PanelAjedrez() {
        setPreferredSize(new Dimension(ancho, alto));
        setBackground(Color.black);
        addMouseMotionListener(mouse);
        addMouseListener(mouse);
        
        setPiezas();
        copiarPiezas(piezas, simPiezas);
       
    }
    public void correrJuego() {
        EjecutarThread = new Thread(this);
        EjecutarThread.start();
    }
    public void setPiezas() {
        
        //los blancos
        piezas.add(new Peon(blanco,0,6));
        piezas.add(new Peon(blanco,1,6));
        piezas.add(new Peon(blanco,2,6));
        piezas.add(new Peon(blanco,3,6));
        piezas.add(new Peon(blanco,4,6));
        piezas.add(new Peon(blanco,5,6));
        piezas.add(new Peon(blanco,6,6));
        piezas.add(new Peon(blanco,7,6));
        piezas.add(new Torre(blanco,0,7));
        piezas.add(new Caballo(blanco,1,7));
        piezas.add(new Alfil(blanco,2,7));
        piezas.add(new Reina(blanco,3,7));
        piezas.add(new Rey(blanco,4,7));
        piezas.add(new Alfil(blanco,5,7));
        piezas.add(new Caballo(blanco,6,7));
        piezas.add(new Torre(blanco,7,7));
        
        //los negros
        piezas.add(new Peon(negro,0,1));
        piezas.add(new Peon(negro,1,1));
        piezas.add(new Peon(negro,2,1));
        piezas.add(new Peon(negro,3,1));
        piezas.add(new Peon(negro,4,1));
        piezas.add(new Peon(negro,5,1));
        piezas.add(new Peon(negro,6,1));
        piezas.add(new Peon(negro,7,1));
        piezas.add(new Torre(negro,0,0));
        piezas.add(new Caballo(negro,1,0));
        piezas.add(new Alfil(negro,2,0));
        piezas.add(new Reina(negro,3,0));
        piezas.add(new Rey(negro,4,0));
        piezas.add(new Alfil(negro,5,0));
        piezas.add(new Caballo(negro,6,0));
        piezas.add(new Torre(negro,7,0));
        
    }
    private void copiarPiezas(ArrayList<Pieza> source, ArrayList<Pieza> Target) {
        
        Target.clear();
        for(int i = 0; i < source.size(); i++) {
            Target.add(source.get(i));
            
            
        }
        
    }
     @Override
    public void run() {
       //loop del juego
       
       double drawInterval = 1000000000/FPS;
       double delta = 0;
       long lastTime = System.nanoTime();
       long currentTime;
       
       while(EjecutarThread != null) {
           
           currentTime = System.nanoTime();
           
           delta += (currentTime - lastTime)/drawInterval;
           lastTime = currentTime;
           
           if(delta >= 1) {
               update();
               repaint();
               delta--;
               
            }
        }
    }
    private void update() {
        
        //Boton del mouse presionado
        if(mouse.pressed){
            if(activarP == null){
                
                //Si activarP es nulo, comprueba si se puede recoger una pieza
                for(Pieza pieza : simPiezas){
                    if(pieza.color == colorActual && pieza.col == mouse.x/Tablero.tamano_cuadro && pieza.fil == mouse.y/Tablero.tamano_cuadro){
                        activarP = pieza;
                    }
                }
            }
        }
        else{
            //Si el jugador tiene una pieza, se simulara el movimiento
            simulate();
        }
            
            //Boton del mouse suelto
            if(mouse.pressed == false){
                if(activarP != null){
                    if(validarCuadro){
                        
                        activarP.actualizarPosicion();
                        
                    } else{
                        activarP.reiniciarPosicion();
                        activarP = null;
                    }
                }
            }
            
    }
    
    private void simulate(){
        puedeMover = false;
        validarCuadro = false;
        
        //Si una pieza está siendo retenida, actualiza su posición
        activarP.x = mouse.x - Tablero.mitad_cuadro;
        activarP.y = mouse.y - Tablero.mitad_cuadro;
        activarP.col = activarP.getCol(activarP.x);
        activarP.fil = activarP.getFil(activarP.y);
        
        //Comprueba si la pieza está flotando sobre un cuadro accesible
        if (activarP.puedeMover(activarP.col, activarP.fil)) {
            
            puedeMover = true;
            validarCuadro = true;
        }
        
    }
        
    @Override
    public void paintComponent(Graphics a) {
        super.paintComponent(a);
        
        Graphics2D a2 = (Graphics2D)a;
        
        tablero.draw(a2);
        
        for(Pieza p : simPiezas) {
            p.dibujar(a2);
        }
        if (activarP != null) {
            a2.setColor(Color.white);
            a2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
            a2.fillRect(activarP.col * Tablero.tamano_cuadro, activarP.fil * Tablero.tamano_cuadro, Tablero.tamano_cuadro, Tablero.tamano_cuadro);
            a2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
            
            //Dibuja la pieza activa al final para que no quede oculta por el tablero o el cuadrado de color
            activarP.dibujar(a2);
        }
    }
}
