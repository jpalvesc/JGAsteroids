/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;

import Util.Posicao;

/**
 *
 * @author jpalvesc
 */
public class Nave {
    
    int vidas=3;
    Posicao p = new Posicao(0, 0, 0);
    int count;
    int compare=10;
    boolean movendo;

    public boolean isMovendo() {
        return movendo;
    }

    public void setMovendo(boolean movendo) {
        this.movendo = movendo;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }
    
    public void Reiniciar(){
        
        vidas=3;
        p = new Posicao(0, 0, 0);
        
    }
    
    public void Criar(){
        
    }
    
//    public void Desenhar(){
//        
//        if(vidas>0){
//            switch(){
//                
//            }
//        }
//    }
}
