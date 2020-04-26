/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interstellar;

import Sgetch.Sgetch;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.awt.Toolkit;

/**
 *
 * @author massimo
 */
public class Interstellar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        char car;
        Boolean esci=false;
        int livelloDifficolta;
        
        
        /*Audio.Tone.Beep(300,0.5);
        Audio.StdAudio.play(Audio.StdAudio.note(300, 0.5, 1.5));
        */
        /* INTRO */
        Interstellar.introduzione();
                
        Universo gioco= new Universo(1);
        
        /* Inizializzo lo spazio del gioco */
        gioco.init();
        
        /*Setto il livello di difficoltà */	
        livelloDifficolta=Interstellar.inserisciIntero("Livello di difficoltà", true);
        if (livelloDifficolta< 1 || livelloDifficolta> 10)
            livelloDifficolta = 1;
	gioco.setLevelDifficulty((double)livelloDifficolta/10.0);

        
        /* mando avanti il primo step del gioco */
        gioco.avanza();
        gioco.disegna();
        
        // loop del gioco
        while(!esci){
            //System.out.println("premi un tasto seguito da ENTER");
            car=Sgetch.premiTasto();  
            switch (car) {
                /*case '\r':
                case '\n':
                    break;*/
                case 'n':
                    gioco.muoviCarroArmato(-1);
                    break;
                case 'm':
                    gioco.muoviCarroArmato(1);
                    break;
                case 'q':
                    esci=true;
                    break;
                default:
                    Sgetch.clearScreen();
                    if (!gioco.isFineGioco()){
                        gioco.avanza();
                        gioco.disegna();    
                    } else {
                        esci=true;
                        System.out.println("*******************");                         
                        System.out.println("**** GAME OVER ****");                     
                        System.out.println("*******************");                             
                    }
            }
        }
        
        
        
    }
 
    
    static void introduzione(){
	
	int i,j,k;
        
        Sgetch.clearScreen();
        
	System.out.println("");
	
	System.out.println("                         ");
	System.out.println("                     .:' ");
	System.out.println("         ....     _.::'  ");
	System.out.println("       .:-\"\"-:.  (_.'    ");
	System.out.println("     .:/      \\:.        ");
	System.out.println("     :|        |:        ");
	System.out.println("     ':\\      /:'        ");
	System.out.println("      '::-..-::'         ");
	System.out.println("        `''''`           ");
	Sgetch.premiTasto();
	
	for (i=0;i<10;i++){
		Sgetch.clearScreen();
		for(j=0;j<i;j++)
			System.out.println("");
		for(k=0;k<(23-i);k++)
			System.out.print(" ");
		System.out.println("    .:' ");
		for(k=0;k<(23-i);k++)
			System.out.print(" ");
		System.out.println(" _.::'  ");
		for(k=0;k<(23-i);k++)
			System.out.print(" ");
		System.out.println("(_.'    ");
		for(k=0;k<(23-i);k++)
			System.out.print(" ");
		System.out.println("        ");
		
            //Audio.Tone.Beep(300,0.1);   
            Audio.StdAudio.play(Audio.StdAudio.note(300+i*15, 0.15, 0.09*i));
            try {                
                Thread.sleep(50);                
            } catch (InterruptedException ex) {
                Logger.getLogger(Interstellar.class.getName()).log(Level.SEVERE, null, ex);
            }
	}

	
	Sgetch.clearScreen();
	for(j=0;j<8;j++)
		System.out.println("");
	System.out.println("      INTERSTELLAR       ");
	System.out.println("                         ");
	System.out.println("                     .:' ");
	System.out.println("         ....     _.::'  ");
	System.out.println("       .:-\"\"-:.  (_.'    ");
	System.out.println("     .:/      \\:.        ");
	System.out.println("     :|        |:        ");
	System.out.println("     ':\\      /:'        ");
	System.out.println("      '::-..-::'         ");
	System.out.println("        `''''`           ");
	
        
        Audio.StdAudio.play(Audio.StdAudio.note(450, 0.2, 0.9));
        Audio.StdAudio.play(Audio.StdAudio.note(600, 0.3, 0.9));
        Audio.StdAudio.play(Audio.StdAudio.note(450, 0.2, 0.9));
                    
        try {
            Thread.sleep(60); // ritardo
        } catch (InterruptedException ex) {
            Logger.getLogger(Interstellar.class.getName()).log(Level.SEVERE, null, ex);
        }
	System.out.print("Premi INVIO per cominciare l'avventura");
        
        Scanner input=new Scanner(System.in);
        String s=input.nextLine();
        
        
	Sgetch.premiTasto();
    }
    
    static int inserisciIntero(String label, Boolean clrScreen){
	int intero;
	String descrLivello[]  = new String[] {"LATTANTI","ELEMENTARE","FACILE","IMPEGNATIVO","DIFFICILE","DIFFICILISSIMO","SEI UN TEMERARIo?","AI CONFINI DELLA REALTA\'","PER FUORI DI TESTA!!","HAHAHAH HAI GIA\' PERSO"};
	
	if (clrScreen)
		Sgetch.clearScreen();
	
        System.out.println("");
	System.out.print("**********************************************\n");
	System.out.print("***                                        ***\n");
	System.out.print("    Inserisci "+label+" : ");
        Scanner input=new Scanner(System.in);
        intero=input.nextInt();
	System.out.print("***                                        ***\n");
	//System.out.print("***  Hai scelto il livello :               ***\n");
	System.out.print("     Hai scelto il livello :               \n");
	System.out.print("***                                        ***\n");
	System.out.println("     "+descrLivello[intero-1]);
	System.out.print("***                                        ***\n");
	System.out.print("     Premi un tasto per continuare            \n");
	System.out.print("***                                        ***\n");
	System.out.print("**********************************************\n");
	
        input=new Scanner(System.in);
        String s=input.nextLine();
        
	return intero;
}


}
