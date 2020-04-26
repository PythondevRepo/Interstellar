#include <stdio.h>
#include <stdlib.h>
#include <conio.h> 
#include <time.h>
#include <windows.h>

#define ALTEZZA 20
#define BASE 30
#define MAXMETEORE 20

#define TRUE 1
#define FALSE 0

/*-------------------------------------------------*\
|*----                                          ---*|
|*----   Definizione strutture dati             ---*|
|*----                                          ---*|
|*----  %MP% Didattica 3C 22/04/2020            ---*|
|*----                                          ---*|
\*-------------------------------------------------*/


typedef struct S_meteora{
    int X;
    int Y;
    int Vx;
    int Vy;
    char simbolo;
    int isInTheSpace; // 1 vero - 0 falso
} Meteora;

typedef struct S_carroarmato{
    int X;
    int vita;
    int scudo;
    char simbolo;
    int colpito; // 1 vero - 0 falso
} CarroArmato;

void delay(int milliSeconds) 
{ 
    // Converting time into milli_seconds 
    int milli_seconds = milliSeconds; 
  
    // Storing start time 
    clock_t start_time = clock(); 
  
    // looping till required time is not achieved 
    while (clock() < start_time + milli_seconds); 
} 

/*-------------------------------------------------*\
|*----                                          ---*|
|*----   Definizione variabili globali          ---*|
|*----                                          ---*|
|*----  %MP% Didattica 3C 22/04/2020            ---*|
|*----                                          ---*|
\*-------------------------------------------------*/

Meteora spazio[BASE][ALTEZZA];

Meteora meteore[MAXMETEORE];

int numMeteore=2; // numero di meteore iniziali

CarroArmato tank;

int levelDifficulty; // livello di difficolta da 0 a 10

/*private CarroArmato tank;

private double levelDifficulty;
*/

/*-------------------------------------------------*\
|*----                                          ---*|
|*----  Funzioni che gestiscono il carro armato ---*|
|*----                                          ---*|
|*----  %MP% Didattica 3C 22/04/2020            ---*|
|*----                                          ---*|
\*-------------------------------------------------*/


int initCarroArmato(int X, int vita, int scudo) {
	
	int errore = FALSE;
	
    tank.X = X;
    tank.vita = vita;
    tank.scudo = scudo;
    tank.simbolo='W';
    tank.colpito = FALSE;

	return errore;
}

void muoviCarroArmato(int deltaX, int limite){
    tank.X+=deltaX;
    if(tank.X >= limite)
        tank.X=limite-1;
    else if (tank.X < 0)
        tank.X=0;
}

/*-------------------------------------------------*/
/*-------FINE carro armato ------------------------*/
/*-------------------------------------------------*/

/*--------------------------------------------*\
|*----                                     ---*|
|*----  Funzioni che gestiscono la meteora ---*|
|*----                                     ---*|
|*----  %MP% Didattica 3C 22/04/2020       ---*|
|*----                                     ---*|
\*--------------------------------------------*/

int initMeteora(int indice,int X, int Y, int Vx, int Vy) {
	
	int errore = FALSE;
	
   	if (indice < MAXMETEORE){
	    meteore[indice].X = X;
	    meteore[indice].Y = Y;
	    meteore[indice].Vx = Vx;
	    meteore[indice].Vy = Vy;
	    meteore[indice].simbolo='*';
	    meteore[indice].isInTheSpace = TRUE;
	} else 
		errore = TRUE;
	return errore;
}

    void muoviMeteora(int indice,int limiteX, int limiteY){
        
        meteore[indice].X+=meteore[indice].Vx;
        if(meteore[indice].X >= limiteX){
            meteore[indice].X=limiteX-1;            
            meteore[indice].Vx=-meteore[indice].Vx;
            //this.isInTheSpace=false; // è uscita fuori dallo spazio consentito
        } else if (meteore[indice].X < 0){
            meteore[indice].X=0;
            meteore[indice].Vx=-meteore[indice].Vx;
            //this.isInTheSpace=false; // è uscita fuori dallo spazio consentito
        }
        
        meteore[indice].Y+=meteore[indice].Vy;  
        if(meteore[indice].Y >=limiteY ){
            meteore[indice].Y=limiteY-1;
            meteore[indice].isInTheSpace=FALSE; // si è schiantata sulla linea di terra
        }
        
    }
    
// Verifica se la meteorite di indice indice ha colpito il carroArmato
int isCrashedTank(indice){
    int ret=FALSE;
    if(meteore[indice].X==tank.X)
        ret=TRUE;
    return ret;        
}

/*-------------------------------------------------*/
/*-------FINE Meteora      ------------------------*/
/*-------------------------------------------------*/

/*--------------------------------------------*\
|*----                                     ---*|
|*----  Funzioni che gestiscono l'universo ---*|
|*----                                     ---*|
|*----  %MP% Didattica 3C 22/04/2020       ---*|
|*----                                     ---*|
\*--------------------------------------------*/

// inizializza l'universo 
int initUniverso(){
	
    int errore;
    
    /* inizializzo meteore */
    errore = initMeteora(0,1,0,1,1);
	errore = initMeteora(1,5,0,0,1);	
	
    
    /* inizializzo carroarmato */
    errore = initCarroArmato(5,2,1);
    
    return errore;
}

// pulisce matrice dello spazio
void cancellaSpazio(){
    int i,j;
    
    for (j=0; j<ALTEZZA; j++)
        for(i=0; i<BASE; i++)
            spazio[i][j].isInTheSpace=FALSE; //lo tolgo dallo spazio
}

// elimina una meteora dall'array delle meteore
void cancellaMeteora(int indiceMeteora){
    int i;
    for(i=indiceMeteora; i<numMeteore-1; i++)
        meteore[i]=meteore[i+1];
    numMeteore--;
}

int creaMeteora(){
    int X,Y;
    int Vx,Vy;
    int creata=TRUE;
    int ran;
    
    ran=(int)rand()%10;
    
  	//printf ("\n\n%d %d %d %d\n\n",ran,levelDifficulty,numMeteore,MAXMETEORE);
    if(ran<=levelDifficulty && numMeteore<MAXMETEORE){ // aggiunge un nuovo meteorite
    
        X=(int)(rand()%BASE);
        Y=-1;        
        if (levelDifficulty >= 5){		
			Vx=(int)(rand()%4);
	        Vy=(int)(rand()%2)+1;
		} else {
			Vx=0;
		    Vy=1;
		}
        
        
        initMeteora(numMeteore,X,Y,Vx,Vy);
        numMeteore++;
    } else 
		creata = FALSE;
	
	return creata;       
    
}

// manda avanti il gioco di un passo
void avanza(){
	int i;
    cancellaSpazio();
    // inserisce randomicamente dei meteoriti
    // a seconda del livello di difficoltà        
    creaMeteora();
    
    for(i=0; i< numMeteore; i++){
        muoviMeteora(i,BASE,ALTEZZA);
		////meteore[i];
        ////meteora.muovi(this.BASE, this.ALTEZZA);
        if (!meteore[i].isInTheSpace){ // se la meteora è uscita dallo spazio
            if(isCrashedTank(i)){ // verifico se si è schiantata col carroarmato
                tank.simbolo = '='; // simbolo di carroarmato distrutto
                tank.colpito = TRUE;
                Beep(300,200);
            }                
            spazio[meteore[i].X][meteore[i].Y].isInTheSpace = FALSE; // cancello meteora dallo spazio
            cancellaMeteora(i); // elimina la meteora iesima dall'array
        } else
            spazio[meteore[i].X][meteore[i].Y]=meteore[i];            
    }
}

// disegna il canvass del gioco
void disegna(){
    int i,j;
    
    // disegna spazio
    // bordo orizzontale superiore
    for(i=0; i<BASE+2; i++)
        printf("%c",'-');
    printf("\n");

    for (j=0; j<ALTEZZA; j++){
        printf("%c",'|'); // bordo verticale destro
        for(i=0; i<BASE; i++){
            if (spazio[i][j].isInTheSpace)
                printf("%c",spazio[i][j].simbolo);
            else 
                printf(" ");
        }
        printf("|"); // bordo verticale sinistro
        
        printf("\n"); // passa a successiva linea dello spazio interstellare 
       
    }
    
    // disegna terra
    /*if (!tank.colpito){
    	printf("%c",'|'); // bordo sinistro
        for(i=0; i<BASE; i++)
            if (tank.X == i)
                printf("%c",tank.simbolo);
            else 
                printf("_");
        printf("%c",'|'); // bordo destro
    } else {
        printf("COLPITO - HAI ANCORA %d PUNTI VITA!\n",tank.vita);
        tank.vita--;
        tank.colpito = FALSE;
        tank.simbolo = 'Y';
        disegna();
    }*/
    
    // disegna terra	
    printf("%c",'|'); // bordo sinistro
    for(i=0; i<BASE; i++)
        if (tank.X == i)
            printf("%c",tank.simbolo);
        else 
            printf("_");
    printf("%c",'|'); // bordo destro
    if (tank.colpito){
    	printf("COLPITO - HAI ANCORA %d PUNTI VITA!\n",tank.vita);
        tank.vita--;
        tank.colpito = FALSE;
        tank.simbolo = 'W';
	} 
}  

// controlla quando il gioco è terminato
int isFineGioco(){
    int ret = FALSE;
    if( tank.vita < 0)
        ret=TRUE;
    return ret;
}


/*-------------------------------------------------*/
/*-------FINE Universo     ------------------------*/
/*-------------------------------------------------*/


/*--------------------------------------------*\
|*----                                     ---*|
|*----  Main                               ---*|
|*----                                     ---*|
|*----  %MP% Didattica 3C 25/04/2020       ---*|
|*----                                     ---*|
\*--------------------------------------------*/
void introduzione(){
	
	int i,j,k;
	
	system("cls");
	printf("                         \n");
	printf("                     .:' \n");
	printf("         ....     _.::'  \n");
	printf("       .:-""-:.  (_.'    \n");
	printf("     .:/      \\:.        \n");
	printf("     :|        |:        \n");
	printf("     ':\\      /:'        \n");
	printf("      '::-..-::'         \n");
	printf("        `''''`           \n");
	getch();
	
	for (i=0;i<10;i++){
		system("cls");
		for(j=0;j<i;j++)
			printf("\n");
		for(k=0;k<(23-i);k++)
			printf(" ");
		printf("    .:' \n");
		for(k=0;k<(23-i);k++)
			printf(" ");
		printf(" _.::'  \n");
		for(k=0;k<(23-i);k++)
			printf(" ");
		printf("(_.'    \n");
		for(k=0;k<(23-i);k++)
			printf(" ");
		printf("        \n");
		
		Beep(300+i*15,150);
		delay(50); // ritardo
	}

	
	system("cls");
	for(j=0;j<8;j++)
		printf("\n");
	printf("      INTERSTELLAR       \n");
	printf("                         \n");
	printf("                     .:' \n");
	printf("         ....     _.::'  \n");
	printf("       .:-""-:.  (_.'    \n");
	printf("     .:/      \\:.        \n");
	printf("     :|        |:        \n");
	printf("     ':\\      /:'        \n");
	printf("      '::-..-::'         \n");
	printf("        `''''`           \n");
	
	delay(50); // ritardo
 	Beep(450,100);
	Beep(600,200);
	Beep(450,100);	
	
	delay(600); // ritardo
	printf("\n Premi un tasto per cominciare l'avventura\n");
    //Beep(400,600);

	getch();
}

int inserisciIntero(char * label, int clrScreen){
	int intero;
	char descrLivello[10][100]  ={"LATTANTI","ELEMENTARE","FACILE","IMPEGNATIVO","DIFFICILE","DIFFICILISSIMO","SEI UN TEMERARIo?","AI CONFINI DELLA REALTA\'","PER FUORI DI TESTA!!","HAHAHAH HAI GIA\' PERSO"};
	
	if (clrScreen)
		system("cls");
		
	printf("**********************************************\n");
	printf("***                                        ***\n");
	printf("    Inserisci %s : ",label);
	scanf("%d",&intero);
	printf("***                                        ***\n");
	//printf("***  Hai scelto il livello :               ***\n");
	printf("     Hai scelto il livello :               \n");
	printf("***                                        ***\n");
	printf("     %s\n",descrLivello[intero-1]);
	printf("***                                        ***\n");
	printf("     Premi un tasto per continuare            \n");
	printf("***                                        ***\n");
	printf("**********************************************\n");
	
	return intero;
}

int main(int argc, char *argv[]) {
	
	int ch;
	int esci = FALSE;
	int pausa = FALSE;
	int velocitaFrame; // 300/levelDifficulty espresso in ms
	
	//int chbit;
	
	srand(time(NULL)); // inizializza il generatore di numeri casuali

	/* INTRO */
	introduzione();
	
	//levelDifficulty=10; // massimo livello di difficoltà
	/* Inserisci livello di difficoltà */	
	levelDifficulty=inserisciIntero("Difficolta' [1 - 10]'", TRUE);
	if (levelDifficulty< 1 || levelDifficulty> 10)
		levelDifficulty = 1;
	
	velocitaFrame=300/levelDifficulty;
		
	ch = getch();
	
	initUniverso();
	
	avanza();
	disegna();
	
	//return 0;
	while(!esci) {
		
		ch=0;
		if (kbhit()){		
		    ch = getch();
            //fflush(stdin);
            //fflush(stdout);
        }
		//printf("premi un tasto\n");
		//ch=getch();
		//printf("Hai premuto %d %c\n",ch,ch);
		switch (ch){
			case 27:
			case 113:
				esci=1;
				break;
			//case 113:
				//printf("\033[H\033[2J");
			//	system ("cls");
			//	break;	
			case 112: // tasto p di pausa
				pausa=!pausa;
				break;
			case 110: /* premuto n */
				muoviCarroArmato(-1,BASE);
				break;	
			case 109: /* premuto m */
				muoviCarroArmato(1,BASE);
				break;		 
			default:			
				system ("cls");
				if (!pausa){
					if(!isFineGioco()){				
						avanza();
						disegna();
					} else {
						esci = TRUE;
						printf("*******************\n");
						printf("**** GAME OVER ****\n");
						printf("*******************\n");
					}
				} else
					disegna();	
		}
		delay(velocitaFrame); // ritardo
	}	
}

