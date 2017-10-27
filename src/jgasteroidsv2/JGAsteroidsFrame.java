package jgasteroidsv2;

import Elementos.Pontuacao;
import Menu.Menu;
import javax.swing.JFrame;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class JGAsteroidsFrame extends JFrame{

    public void start(){
        GLProfile glp = GLProfile.getDefault();
        GLCapabilities caps = new GLCapabilities(glp);
        GLCanvas canvas = new GLCanvas(caps);
        

        super.setTitle("JAsteroids");
        super.setSize(950,700);
        super.setLayout(new BorderLayout());
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.add(canvas, BorderLayout.CENTER);
        super.setLocationRelativeTo(null);
        
        try {
            adicionaIcone();
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JGAsteroidsRender jogo = new JGAsteroidsRender();
        HandlersJogo act = new HandlersJogo(jogo);
        canvas.addGLEventListener(jogo);
        canvas.addKeyListener(act);
        canvas.addMouseListener(act);
        canvas.setVisible(true);
        
        FPSAnimator animator = new FPSAnimator(canvas, 60);
        animator.start();
        
        act.setAnimator(animator);
        
        Pontuacao ponto = new Pontuacao();
        ponto.setAnimator(animator);
        ponto.iniciaContador();
        ponto.setaFrame(this);
        
        super.setVisible(true);
        
    }
    
    private void adicionaIcone() throws IOException{
        BufferedImage icone = ImageIO.read(getClass().getResource("/Imagens/Icone.png"));
        super.setIconImage(icone);
    }
    
}
