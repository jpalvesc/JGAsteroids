/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;

import Util.Posicao;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jpalvesc
 */
public class Meteoro {

    //Variáveis
    static int lista;
    Random r = new Random();
    
    float incX;
    float incY;
    float velocidade;
    float raio;
    float rotacao;

    private float posX = 0.0f;
    private float posY = 0.0f;
    private float posZ = 0.0f;

    boolean impacto;

    Texture textura1;
    Texture textura2;
    TextureData texturaData;

    static GL2 Gl;
    static GLU Glu;
    GLUT glut;

    public boolean isImpacto() {
        return impacto;
    }

    public void setImpacto(boolean impacto) {
        this.impacto = impacto;
    }

    public Meteoro(GLAutoDrawable drawable) {

        rotacao = r.nextInt(90);
        Reset();
        
//        if (r.nextInt(2) == 1) {
            try {
                InputStream stream1 = getClass().getResourceAsStream("/Texturas/asteroide1.jpg");
                texturaData = TextureIO.newTextureData(GLProfile.getDefault(), stream1, false, "jpg");
                textura1 = TextureIO.newTexture(texturaData);
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("Erro na textura 1 " + ex.getMessage());
            }
  /*      } else {

            try {
                InputStream stream2 = getClass().getResourceAsStream("/Texturas/asteroide2.jpg");
                texturaData = TextureIO.newTextureData(GLProfile.getDefault(), stream2, false, "jpg");
                textura1 = TextureIO.newTexture(texturaData);
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("Erro na textura 2 " + ex.getMessage());
            }
        }
    */    
        Criar(drawable);

    }

    public void Reset() {

     //   posX = r.nextInt(35) * -1;
        
//posX = (r.nextInt(25)) * (float) Math.pow(-1, r.nextInt());
//      posY = 5;
//      posZ = 0;
//       posY = (r.nextInt(20)) * (float) Math.pow(-1, r.nextInt());
//        posZ = (30 + (r.nextInt(45))) * -1;

//X: 0.0, Y: 8.5, Z: 9.0, Veloc 0.0

        posX = ((r.nextInt(11))) * (float)Math.pow(-1, r.nextInt());
        posY = (4+ (r.nextInt(35)));
        posZ = (r.nextInt(8)) * (float)Math.pow(1, r.nextInt());
      //  posZ = r.nextInt(8);
        raio = (float) (r.nextDouble() * 2);

        if (getPosX() > 0) {
            incX = (float) r.nextDouble() * -1;
           // System.out.println(" "+incX+ " if getposX1");
        } else {
            incX = (float) r.nextDouble();
            //System.out.println(" "+incX+ " if getposX2");
        }

        if (getPosY() > 0) {
            incY = (float) r.nextDouble() * -1;
            //System.out.println(" "+incY+ " if getposY1");
        } else {
            incY = (float) r.nextDouble();
            //System.out.println(" "+incY+ " if getposY2");
        }

        incX *= 0.03f;
        incY *= 0.05f;
        velocidade = (float) (r.nextDouble());
    }

    public void Criar(GLAutoDrawable drawable) {
        Glu = new GLU();
        Gl = drawable.getGL().getGL2();
        
        //Habilita a textura
//	textura1.enable(Gl);
//	textura1.bind(Gl);

        lista = Gl.glGenLists(1); // Cria a lista 
        Gl.glNewList(lista, Gl.GL_COMPILE);
        Gl.glPushMatrix();
            GLUquadric quadratic = Glu.gluNewQuadric(); //Criar o objeto quadrico
            Glu.gluQuadricNormals(quadratic, Glu.GLU_SMOOTH);
            Glu.gluQuadricTexture(quadratic, true);
            Glu.gluQuadricDrawStyle(quadratic, GLU.GLU_FILL);
            Glu.gluQuadricOrientation(quadratic, GLU.GLU_OUTSIDE);
            Gl.glRotated(270, 1, 0, 0);
            Glu.gluSphere(quadratic, 1, 5, 5); //Criou a esfera 
            Glu.gluDeleteQuadric(quadratic);
        Gl.glPopMatrix();
        Gl.glEndList();
        
        
//        textura1.disable(Gl);
        
    }

    public void Desenha(GL2 Gl) {
        posZ += velocidade;
        posY += incY;
        posX += incX;
        rotacao += 1f;

        textura1.enable(Gl);
	textura1.bind(Gl);
        
        Gl.glPushMatrix();
            Gl.glTranslatef(posX, posY, posY);
            Gl.glRotatef(rotacao, 1, 1, 1);
            Gl.glCallList(lista);
        Gl.glPopMatrix();
        
        textura1.disable(Gl);

        if (getPosZ() < -8) {
            Reset();
        }
        //Gl.glDisable(Gl.GL_TEXTURE_2D);
    }

    public float getPosX() {

        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public float getPosZ() {
        return posZ;
    }

    public void setPosZ(float posZ) {
        this.posZ = posZ;
    }
    
    
}
