package jgasteroidsv2;


import Elementos.Foguete;
import Elementos.GeradorMeteoro;
import Elementos.Meteoro;
import Elementos.Pontuacao;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class JGAsteroidsRender implements GLEventListener{

    private GL2 gl;
    private GLUT glut = new GLUT();
    private GLU glu= new GLU();
    
    private int console = 0, controleMeteoro = 0;
   private float raio;
    private Foguete fog;
//    private Meteoro meteoro;

    private GeradorMeteoro meteoro = new GeradorMeteoro();
    private Meteoro meteor;
    
    private int idTextura[];
    private int largura, altura;
    private BufferedImage imagem;
    private TextureData td;
    private ByteBuffer buffer;
    private GLAutoDrawable glDrawable;
    
    public JGAsteroidsRender(){
        fog = new Foguete();
        meteoro = new GeradorMeteoro();
       
    }
    
    
    @Override
    public void init(GLAutoDrawable drawable) {
        glDrawable = drawable;
        gl = drawable.getGL().getGL2();
        glu = new GLU();
        glut = new GLUT();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glEnable(GL.GL_DEPTH_TEST);    

        //Habilita a ilumina��o
        gl.glEnable(GL2.GL_LIGHT0);
        gl.glEnable(GL2.GL_LIGHT1);
        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_COLOR_MATERIAL);
        gl.glColorMaterial(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE);	

        // Habilita o modelo de coloriza��o de Gouraud
        gl.glShadeModel(GL2.GL_SMOOTH);

        // Comandos de inicializa��o para textura
        //loadImage("nave.jpg");

        // Gera identificador de textura
        idTextura = new int[10];
        gl.glGenTextures(1, idTextura, 1);

        // Especifica qual � a textura corrente pelo identificador 
        gl.glBindTexture(GL.GL_TEXTURE_2D, idTextura[0]);	

        // Envio da textura para OpenGL
        gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, 3, largura, 
                        altura, 0, GL.GL_BGR, GL.GL_UNSIGNED_BYTE, buffer);

        // Define os filtros de magnifica��o e minifica��o 
        gl.glTexParameteri(GL.GL_TEXTURE_2D,GL.GL_TEXTURE_MIN_FILTER,GL.GL_LINEAR);	
        gl.glTexParameteri(GL.GL_TEXTURE_2D,GL.GL_TEXTURE_MAG_FILTER,GL.GL_LINEAR);
    }
    
    public void loadImage(String fileName)
    {
        // Tenta carregar o arquivo		
        imagem = null;
        try {
                imagem = ImageIO.read(new File("src\\texture\\" + fileName));
                // Obt�m largura e altura
                largura  = imagem.getWidth();
                altura = imagem.getHeight();
        }
        catch (IOException e) {
                JOptionPane.showMessageDialog(null,"Erro na leitura do arquivo");
                System.out.println(e.getStackTrace());
        }

        //Carrega a textura		
        try {
                InputStream stream = getClass().getResourceAsStream(fileName);
                td = TextureIO.newTextureData(GLProfile.getDefault(), stream, false, "jpg");
        }
        catch (IOException exc) {
                exc.printStackTrace();
                System.exit(1);
        }
        // ...e obt�m um ByteBuffer a partir dela
        buffer = (ByteBuffer) td.getBuffer();
    }

    @Override
    public void dispose(GLAutoDrawable glad) {}

    @Override
    public void display(GLAutoDrawable glad) {
        console++;
        
        GL2 gl = glad.getGL().getGL2();
        
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
        
        //ILUMINAÇÃO
        gl.glEnable(GL2.GL_LIGHT0);
        gl.glEnable(GL2.GL_LIGHT1);
        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_COLOR_MATERIAL);
        gl.glColorMaterial(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE);
        //FIM ILUMINAÇÃO
        // Habilita o modelo de coloriza��o de Gouraud
	gl.glShadeModel(GL2.GL_SMOOTH);
        
        gl.glMatrixMode(GL2.GL_PROJECTION);        	
        gl.glLoadIdentity();
        glu.gluPerspective(90.0, 1, 0.5, 200.0);
        
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity(); 
//        glu.gluLookAt(0, 1.5, fog.getPosZ()-10, 0, 0, fog.getPosZ(), 0, 1, 0);
        glu.gluLookAt(0, 0, -10,  0, 0, 0,  0, 1, 0);
        
        gl.glRotatef(0,0,1f,0);
        
        fog.desenhaFoguete(gl, glut);
//        meteoro.Desenha();
        
        
        meteoro.DesenhaMeteoros(gl);
                //meteoro.GerarMeteoro(glad, 2, false);
        controleMeteoro++;
        meteoro.GeraMeteoro(glad, controleMeteoro);
       // meteoro.checarColisao(fog);
        
        if(meteoro.checarColisao(fog,raio)==true){
            gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
            display(glad);         
        }
        gl.glFlush();
     
        //para controlar a saída do console
        if(console % 30 == 0){
            System.out.println("X: " + fog.getPosX() + ", Y: " + fog.getPosY() + ", Z: " + fog.getPosZ() + ", Veloc " + fog.getVel());
            console = 0;
        }
        
    }
    
    @Override
    public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {}
    
    public Foguete getFog(){
        return fog;
    }

    
}
