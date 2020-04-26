/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interstellar;

/**
 *
 * @author massimo
 */
public class CarroArmato {
    private int X;
    private int vita;
    private int scudo;
    private char simbolo;
    private Boolean colpito;

    public CarroArmato(int X, int vita, int scudo) {
        this.X = X;
        this.vita = vita;
        this.scudo = scudo;
        this.simbolo='W';
        this.colpito=false;
    }

    public int getX() {
        return X;
    }

    public void setX(int X) {
        this.X = X;
    }

    public int getVita() {
        return vita;
    }

    public void setVita(int vita) {
        this.vita = vita;
    }

    public int getScudo() {
        return scudo;
    }

    public void setScudo(int scudo) {
        this.scudo = scudo;
    }
        
    public char getSimbolo() {
        return this.simbolo;
    }

    public void setSimbolo(char simbolo) {
        this.simbolo = simbolo;
    }

    public Boolean getColpito() {
        return colpito;
    }

    public void setColpito(Boolean colpito) {
        this.colpito = colpito;
    }
    
    public void muovi(int deltaX, int limite){
        this.X+=deltaX;
        if(this.X >= limite)
            this.X=limite-1;
        else if (this.X < 0)
            this.X=0;
    }
    
}
