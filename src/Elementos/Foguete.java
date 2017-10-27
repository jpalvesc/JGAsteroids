package Elementos;

import Util.Posicao;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Foguete {

    private float posX = 0.0f;
    private float posY = 0.0f;
    private float posZ = 0.0f;
    public Posicao p = new Posicao(0, 0, 0);
    private float vel = 0.0f;
    private int idTextura[];
    private int largura, altura;
    private BufferedImage imagem;
    private TextureData td;
    private ByteBuffer buffer;

    TextureData textureData;
    Texture texturaNave;

//    private void textura(){
//        try {
//                InputStream stream1 = getClass().getResourceAsStream("/Texturas/texturaNave.png");
//                textureData = TextureIO.newTextureData(GLProfile.getDefault(), stream1, false, "png");
//                texturaNave = TextureIO.newTexture(textureData);
//        } catch (Exception e) {
//             e.printStackTrace();
//             System.out.println("Erro na textura da nave: " + e.getMessage());
//        }
//    }
    public void desenhaFoguete(GL2 gl, GLUT glut) {
        //foguete
        gl.glPushMatrix();
        gl.glScalef(1f, 1f, 1f);
        gl.glTranslatef(getPosX(), getPosY() - 2.5f, getPosZ() - 3);
        gl.glRotatef(0, 0.0f, 0.0f, 0.0f);

        //corpo
        gl.glPushMatrix();
        gl.glColor3f(0.7f, 0.7f, 0.7f);
        gl.glScalef(1.0f, 1.0f, -3.0f);
        gl.glTranslatef(0.0f, 0.0f, 0.0f);
        glut.glutSolidCylinder(1, 1, 8, 6);
                glut.glutWireCylinder(1, 1, 8, 6);
        gl.glPopMatrix();

//flaps foguete
        //superior
        gl.glPushMatrix();
        gl.glColor3f(0.5f, 0.5f, 0.5f);
        gl.glScalef(0.1f, 0.35f, -1.0f);
        gl.glTranslatef(0.0f, 3.0f, 2.5f);
        glut.glutSolidCube(1.0f);
                glut.glutWireCube(1.0f);
        gl.glPopMatrix();
        //direita
        gl.glPushMatrix();
        gl.glScalef(0.35f, 0.1f, -1.0f);
        gl.glTranslatef(3.0f, 0.0f, 2.5f);
        glut.glutSolidCube(1.0f);
                glut.glutWireCube(1.0f);
        gl.glPopMatrix();

        //esquerda
        gl.glPushMatrix();
        gl.glScalef(0.35f, 0.1f, -1.0f);
        gl.glTranslatef(-3.0f, 0.0f, 2.5f);
        glut.glutSolidCube(1.0f);
               glut.glutWireCube(1.0f);
        gl.glPopMatrix();

        //base superior
        gl.glPushMatrix();
        gl.glColor3f(0.8f, 0.8f, 0.8f);
        gl.glScalef(1.0f, 1.0f, 0.0f);
        gl.glTranslatef(0.0f, 0.0f, 0.0f);
        glut.glutSolidCone(1, 3, 8, 8);
                glut.glutWireCone(1, 3, 8, 8);
        gl.glPopMatrix();
        //base inferior
        gl.glPushMatrix();
        gl.glColor3f(0.9f, 0.9f, 0.9f);
        gl.glScalef(1.0f, 1.0f, -0.01f);
        gl.glTranslatef(0.0f, 0.0f, 300.0f);
        gl.glRotatef(180, 1f, 1f, 0f);
        glut.glutSolidCone(1, 3, 8, 8);
               glut.glutWireCone(1, 3, 8, 8);
        gl.glPopMatrix();

        //bico foguete
        gl.glPushMatrix();
        gl.glColor3f(0f, 1f, 0f);
        gl.glScalef(1.0f, 1.0f, 0.75f);
        gl.glTranslatef(0.0f, 0.0f, 0.0f);
        glut.glutSolidCone(1, 3, 8, 8);
                glut.glutWireCone(1, 3, 8, 8);
        gl.glPopMatrix();

        //turbinas foguete
        //direita superior
        gl.glPushMatrix();
        gl.glColor3f(1f, 0.5f, 0.5f);
        gl.glScalef(0.25f, 0.25f, -1.0f);
        gl.glTranslatef(1.0f, 1.0f, 3.0f);
        glut.glutSolidCylinder(1, 0.25, 10, 1);
                glut.glutWireCylinder(1, 0.25, 10, 1);
        gl.glPopMatrix();
        //esquerda superior
        gl.glPushMatrix();
        gl.glColor3f(1f, 0.5f, 0.5f);
        gl.glScalef(0.25f, 0.25f, -1.0f);
        gl.glTranslatef(-1.0f, 1.0f, 3.0f);
        glut.glutSolidCylinder(1, 0.25, 10, 1);
                glut.glutWireCylinder(1, 0.25, 10, 1);
        gl.glPopMatrix();
        //inferior
        gl.glPushMatrix();
        gl.glColor3f(1f, 0.5f, 0.5f);
        gl.glScalef(0.25f, 0.25f, -1.0f);
        gl.glTranslatef(0.0f, -1.0f, 3.0f);
        glut.glutSolidCylinder(1, 0.25, 10, 1);
                glut.glutWireCylinder(1, 0.25, 10, 1);
        gl.glPopMatrix();

        gl.glPopMatrix();

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

    public float getVel() {
        return vel;
    }

    public void setVel(float vel) {
        this.vel = vel;
    }

}
