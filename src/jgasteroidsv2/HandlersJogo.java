package jgasteroidsv2;

import Elementos.Foguete;
import com.jogamp.opengl.util.FPSAnimator;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


class HandlersJogo implements KeyListener, MouseListener{
    
    private JGAsteroidsRender render;
    private Foguete fog;
    
    private FPSAnimator pause;
    private int pauseBin = 0;
    
    public HandlersJogo(JGAsteroidsRender render){
        this.render = render;
        fog = this.render.getFog();
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}
    
    @Override
    public void keyPressed(KeyEvent e) {
        if(pause.isPaused()){
            //faz nada pois está pausado
        }else{
            if (e.getKeyCode() == KeyEvent.VK_A) {
                new Thread(a).start();
                System.out.println("A");
            }
            if (e.getKeyCode() == e.VK_D) {
                new Thread(d).start();
                System.out.println("D");
            }
            if (e.getKeyCode() == KeyEvent.VK_W) {
                new Thread(w).start();
                System.out.println("W");
            }
            if (e.getKeyCode() == e.VK_S) {
                new Thread(s).start();
                System.out.println("S");
            }
        }
        
        if (e.getKeyCode() == e.VK_ESCAPE) {
            if(pauseBin == 0){
                pause.pause();
                pauseBin++;
            }else if(pauseBin > 0){
                pause.resume();
                pauseBin = 0;
            }
        }
        
    }
    
    public void setAnimator(FPSAnimator animator){
        pause = animator;
    }
    
    @Override
    public void keyReleased(KeyEvent e) { 
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        if(pause.isPaused()){
        // faz nada pq está pausado    
        }else{
            if (e.getButton()== MouseEvent.BUTTON1) {
                new Thread(mouse1).start();
                System.out.println("MOUSE1");
            }
            if (e.getButton() == MouseEvent.BUTTON3) {
                new Thread(mouse3).start();
                System.out.println("MOUSE3");
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}


    private Runnable w = new Runnable() {
        public void run() {
            try{
                fog.setPosY(fog.getPosY()+0.5f);
            } catch (Exception e){}
        }
    };
    
    private Runnable a = new Runnable() {
        public void run() {
            try{
                fog.setPosX(fog.getPosX()+0.5f);
            } catch (Exception e){}
        }
    };
    
    private Runnable s = new Runnable() {
        public void run() {
            try{
                fog.setPosY(fog.getPosY()-0.5f);
            } catch (Exception e){}
        }
    };
    
    private Runnable d = new Runnable() {
        public void run() {
            try{
                fog.setPosX(fog.getPosX()-0.5f);
            } catch (Exception e){}
        }
    };
    
    private Runnable mouse1 = new Runnable() {
        public void run() {
            try{
                fog.setPosZ(fog.getPosZ()+0.5f);
            } catch (Exception e){}
        }
    };
    
    private Runnable mouse3 = new Runnable() {
        public void run() {
            try{
                fog.setPosZ(fog.getPosZ()-0.5f);
            } catch (Exception e){}
        }
    };
    
}