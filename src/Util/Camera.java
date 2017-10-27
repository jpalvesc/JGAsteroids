/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

/**
 *
 * @author Giu
 */
public class Camera {
    
    GL2 Gl; 
    GLUT glut;
    GLU Glu;
    
    public void SelecionaCamera(int camera){
        
        //Trabalhando com a matriz modelo
            Gl.glMatrixMode(Gl.GL_MODELVIEW);
            Gl.glLoadIdentity();

            switch (camera)
            {
                case 0:
                    {
                        Glu.gluLookAt(0, 2, 4, 0, 0, -2, 0, 1, 0);
                        break;
                    }
                case 1:
                    {
                        Glu.gluLookAt(0, 3, 5, 0, 1, -4, 0, 1, 0);
                        break;
                    }
                case 2:
                    {
                        Glu.gluLookAt(0, 70, 0, 0, 0, -16, 0, 1, 0);
                        break;
                    }
                case 3:
                    {
                        Glu.gluLookAt(3, 3, -47, 1, 0, 1, 0, 1, 0);
                        break;
                    }
            }
    }
    
     public void SetPerspective()
        {
            //Seleciona a matriz de projeção
            Gl.glMatrixMode(Gl.GL_PROJECTION);
            //A reseto
            Gl.glLoadIdentity();
            //45 = ângulo de visão
            //1  = Proporção de alto por largura
            //1000 = Distância máxima
            Glu.gluPerspective(65, 1, 0.1f, 1000);
            SelecionaCamera(0);
        }
}
