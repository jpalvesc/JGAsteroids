package Elementos;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;
import java.io.InputStream;
import java.nio.ByteBuffer;


/**
 *
 * @author jpalvesc
 */
public class Asteroides {
    
    
    private float posX = 0.0f;
    private float posY = 0.0f;
    private float posZ = 0.0f;
    
    float velocidade;
    float raio;
    float rotacao;
    
    private GL2 gl;
    private TextureData td;
    private ByteBuffer buffer;
    private Texture texturaAsteroid;
    
    GLU glu = new GLU();
    
    public void Criar(){
        
        
        try {
            InputStream stream = getClass().getResourceAsStream("Texturas/asteroide1.jpg");
            TextureData data = TextureIO.newTextureData(GLProfile.getDefault(), stream, false, "jpg");
            texturaAsteroid = TextureIO.newTexture(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        
        texturaAsteroid.enable(gl);
        texturaAsteroid.bind(gl);
        
        gl.glPushMatrix();
        
            GLUquadric asteroid = glu.gluNewQuadric();
            glu.gluQuadricTexture(asteroid, true);
            glu.gluQuadricDrawStyle(asteroid, GLU.GLU_FILL);
            glu.gluQuadricNormals(asteroid, GLU.GLU_FLAT);
        
        }
    }
        

