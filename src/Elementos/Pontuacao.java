package Elementos;

import com.jogamp.opengl.util.FPSAnimator;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Pontuacao {
    
    private FPSAnimator pause;
    private Timer timer;
    private int pontos = 0;
    private JLabel lbPontos;
    private JFrame frame;
    
    public void setAnimator(FPSAnimator animator){
        pause = animator;
    }
    
    public String getPontos(){
        return Integer.toString(pontos);
    }
    
//    public Integer getPontos(){
//        return pontos;
//    }
    
    public void iniciaContador(){
        
        timer = new Timer();
        timer.schedule(new Contabiliza(), 1*300);

    }
    
    class Contabiliza extends TimerTask {
        public void run() {
            if(pause.isPaused()){
                
            }else{
                pontos++;
                System.out.println("PONTOS: " + pontos);
                //timer.cancel();
                desenhaPontos(frame);
                iniciaContador();
            }
            
        }//fim run
    }//fim contabiliza
    
    public void desenhaPontos(JFrame frame){
        lbPontos = new JLabel("PONTUAÇÃO: " + getPontos());  // FALTA MOSTRAR ******************************************
        frame.add(lbPontos, BorderLayout.SOUTH);
    }
    
    public void setaFrame(JFrame frame){
        this.frame = frame;
        desenhaPontos(frame);
        adicionaBotoes();
    }

     
    
    private void adicionaBotoes(){
        
        JButton btPause = new JButton("Pause");
        btPause.setBounds(590,235,100,40);
        
        JButton btResume = new JButton("Continuar");
        btResume.setBounds(590,235,100,40);
    }
}
