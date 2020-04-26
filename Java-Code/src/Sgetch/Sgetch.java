/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sgetch;

/**
 *
 * @author massimo
 */
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author massimo
 */
public class Sgetch {
    
    public static char premiTasto() {
        char car=0;
        //out.printf("\n\n  Press ENTER..");
        

        try {
            car=(char)System.in.read();
        } catch (IOException ex) {
            System.out.println("Errore lettura tastiera");
        }
       
        
        //System.out.println("car= '"+car+"'");
        //System.out.println("---- sto restituendo: "+car);
        return car;
    }
    
    static char premiTastoFiltraCR() {
        char car=0;
        //out.printf("\n\n  Press ENTER..");
        do {
            try {
                car=(char)System.in.read();
            } catch (IOException ex) {
                System.out.println("Errore lettura tastiera");
            }
        }while((char)car == '\n' | (char)car == '\r');

        //System.out.println("car= '"+car+"'");
        //System.out.println("---- sto restituendo: "+car);
        return car;
    }
    
    static int premiTastoOLD(){
        int car=0;
        try{
            //out.printf("\n\n  Press ENTER..");
            car=System.in.read();
            
            car=System.in.read();
            System.out.println("car= '"+car+"'");
        }
        catch(IOException exe){
                //out.printf("Error?");
        }
        return car;
    }
    
    public static void clearScreen() {
        /* Linux */
        /*System.out.print("\033[H\033[2J");
        System.out.flush();*/
        /* Windows */
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException ex) {
            Logger.getLogger(Sgetch.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Sgetch.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

