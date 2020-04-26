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
public class Meteora {
    private int X;
    private int Y;
    private int Vx;
    private int Vy;
    private char simbolo;
    private Boolean isInTheSpace;

    public Meteora(int X, int Y, int Vx, int Vy) {
        this.X = X;
        this.Y = Y;
        this.Vx = Vx;
        this.Vy = Vy;
        this.simbolo='*';
        this.isInTheSpace=true;
    }

    public Meteora(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }

    public int getX() {
        return X;
    }

    public void setX(int X) {
        this.X = X;
    }

    public int getY() {
        return Y;
    }

    public void setY(int Y) {
        this.Y = Y;
    }

    public int getVx() {
        return Vx;
    }

    public void setVx(int Vx) {
        this.Vx = Vx;
    }

    public int getVy() {
        return Vy;
    }

    public void setVy(int Vy) {
        this.Vy = Vy;
    }
    
    public char getSimbolo() {
        return this.simbolo;
    }

    public void setSimbolo(char simbolo) {
        this.simbolo = simbolo;
    }

    public Boolean getIsInTheSpace() {
        return isInTheSpace;
    }

    public void setIsInTheSpace(Boolean isInTheSpace) {
        this.isInTheSpace = isInTheSpace;
    }
        
    public void muovi(int limiteX, int limiteY){
        
        this.X+=this.Vx;
        if(this.X >= limiteX){
            this.X=limiteX-1;            
            this.Vx=-this.Vx;
            //this.isInTheSpace=false; // è uscita fuori dallo spazio consentito
        } else if (this.X < 0){
            this.X=0;
            this.Vx=-this.Vx;
            //this.isInTheSpace=false; // è uscita fuori dallo spazio consentito
        }
        
        this.Y+=Vy;  
        if(this.Y >=limiteY ){
            this.Y=limiteY-1;
            this.isInTheSpace=false; // si è schiantata sulla linea di terra
        }
        
    }
    
    public Boolean isCrashed(CarroArmato tank){
        Boolean ret=false;
        if(this.X==tank.getX())
            ret=true;
        return ret;        
    }
    
}
