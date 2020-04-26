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
public class Universo {
    private Meteora spazio[][];
    private CarroArmato terra[];
    
    private Meteora meteore[];
    private int numMeteore;
    private CarroArmato tank;
    
    private double levelDifficulty;
    
    private final int BASE=20;
    private final int ALTEZZA=20;
    private final int MAXMETEORE=20;
    
    public Universo(double levelDifficulty){
        spazio = new Meteora[this.BASE][this.ALTEZZA];
        terra = new CarroArmato[this.BASE];          
        this.numMeteore=2;
        this.levelDifficulty=levelDifficulty;
    }

    public void setLevelDifficulty(double levelDifficulty) {
        this.levelDifficulty = levelDifficulty;
    }
    
    public void init(){
        int i;
        /* inizializzo meteore */
        meteore = new Meteora[MAXMETEORE];
        
        this.meteore[0]=new Meteora(1, 0, 1, 1);
        this.meteore[1]=new Meteora(5, 0, 0, 1);
        
        /* inizializzo carroarmato */
        tank= new CarroArmato(5,2,1);
    }
    
    private void cancellaSpazio(){
        int i,j;
        
        for (j=0; j<this.ALTEZZA; j++)
            for(i=0; i<this.BASE; i++)
                this.spazio[i][j]=null;
    }
    
    // manda avanti il gioco di un passo
    public void avanza(){
        int i;
        Meteora meteora;
        this.cancellaSpazio();
        // inserisce randomicamente dei meteoriti
        // a seconda del livello di difficoltà        
        this.creaMeteora();
        
        for(i=0; i< this.numMeteore; i++){
            meteora=this.meteore[i];
            meteora.muovi(this.BASE, this.ALTEZZA);
            if (!meteora.getIsInTheSpace()){ // se la meteora è uscita dallo spazio
                if(meteora.isCrashed(this.tank)){ // verifico se si è schiantata col carroarmato
                    this.tank.setSimbolo('=');
                    this.tank.setColpito(true);
                }                
                this.spazio[meteora.getX()][meteora.getY()]=null;
                this.cancellaMeteora(i); // elimina la meteora dall'array
                //System.out.println("------------ "+this.numMeteore);
            } else
                this.spazio[meteora.getX()][meteora.getY()]=meteora;            
        }
    }
    
    // disegna il canvass del gioco
    public void disegna(){
        int i,j;
        
        // disegna spazio
        // bordo orizzontale superiore
        for(i=0; i<this.BASE+2; i++)
            System.out.print("-");
        System.out.println("");

        for (j=0; j<this.ALTEZZA-1; j++){
            System.out.print("|"); // bordo verticale destro
            for(i=0; i<this.BASE; i++){
                if (this.spazio[i][j] instanceof Meteora)
                    System.out.print(this.spazio[i][j].getSimbolo());
                else 
                    System.out.print(" ");
            }
            System.out.print("|"); // bordo verticale sinistro
            System.out.println(""); /* passa a successiva linea dello spazio interstellare */
        }
        
        // disegna terra
        if (!this.tank.getColpito()){
            
            System.out.print("|"); // bordo verticale destro
            for(i=0; i<this.BASE; i++)
                if (this.tank.getX()==i)
                    System.out.print(this.tank.getSimbolo());
                else 
                    System.out.print("_");
            System.out.print("|"); // bordo verticale sinistro
        } else {
            System.out.println("COLPITO - HAI ANCORA "+this.tank.getVita()+" PUNTI VITA!");
            this.tank.setVita(this.tank.getVita()-1);
            this.tank.setColpito(false);
            this.tank.setSimbolo('W');
            this.disegna();
        }
    }  
    
    public void muoviCarroArmato(int deltaX){
        this.tank.muovi(deltaX,this.BASE);
    }
    
    private void cancellaMeteora(int indiceMeteora){
        int i;
        for(i=indiceMeteora; i<this.numMeteore-1; i++)
            this.meteore[i]=this.meteore[i+1];
        this.numMeteore--;
    }
    private void creaMeteora(){
        int X,Y;
        int Vx,Vy;
        
        if(Math.random()<this.levelDifficulty && this.numMeteore<this.MAXMETEORE){ // aggiunge un nuovo meteorite
            X=(int)(Math.random()*this.BASE);
            Y=0;
            if ( this.levelDifficulty >=0.5 ){
                Vx=(int)(Math.random()*3);
                Vy=(int)(Math.random())+1;
            } else {
                Vx=0;
                Vy=1;
            }
            
            this.meteore[this.numMeteore]=new Meteora(X,Y,Vx,Vy);
            this.numMeteore++;
        }        
        
    }
    
    public Boolean isFineGioco(){
        Boolean ret=false;
        if( this.tank.getVita() < 0)
            ret=true;
        return ret;
    }
}
