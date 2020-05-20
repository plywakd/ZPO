
package lab05;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

public class Lab05 {

    //Zad01
    public static void randFreq(){
        int tab[]=new int[10];
        int freqTab[]=new int[5];
	Random generator= new Random();
	for(int i=0;i<tab.length;i++) {
            tab[i]=generator.nextInt(5)+1;
            freqTab[tab[i]-1]++;
	}
        for(int i=0;i<freqTab.length;i++){
            System.out.print("Frequency of:"+(i+1)+"="+freqTab[i]+", ");
        }
    }
    //Zad02
    public static void insertString(){
        int x;
	Scanner wpisz= new Scanner(System.in);
	System.out.println("Podaj ile napisów chcesz wprowadzić: ");
	x=wpisz.nextInt();
        String text[]=new String[x];
        String invertText[]=new String[x];
        for(int i=0;i<text.length;i++){
            System.out.println("Podaj napis"+i+": ");
            text[i]=wpisz.next();
        }
        int j=x-1;
        for(int i=0;i<text.length;i++){
            invertText[i]=text[j];
            j--;
        }
        System.out.print("Tablica początkowa: ");
        for(int i=0;i<text.length;i++){
            System.out.print(text[i]+" ");
        }
        System.out.print("Tablica zamieniona: ");
        for(int i=0;i<invertText.length;i++){
            System.out.print(invertText[i]+" ");
        }
    }
    //Zad03
    public static void insertTab2D(){
        double tab[][]=new double[5][5];
        DecimalFormat upToTwo=new DecimalFormat("0.00");
	Random generator= new Random();
        double avg=0;
	for(int i=0;i<5;i++) {
            avg=0;
            for(int j=0;j<5;j++) {
		tab[i][j]=100*generator.nextDouble();
		System.out.print(upToTwo.format(tab[i][j])+" ");
                avg+=tab[i][j];
	}
            avg/=5;
            System.out.print(" AVG column:"+upToTwo.format(avg)+"\n");
	}
    }
    //Zad04
    public static void indxEvenOdd(){
        int tab[]=new int[10];
        Random generator= new Random();
	for(int i=0;i<tab.length;i++) {
            tab[i]=generator.nextInt(100)+1;
            if(i%2==0)System.out.print(i+":"+true+" |");
            else System.out.print(i+":"+false+" |");
	}
    }
    //Zad07
    public static void det(){
        double param[]=new double[6];//a1,b1,c1 and a2,b2,c2
        Scanner wpisz= new Scanner(System.in);
        System.out.println("Podaj parametry dwóch równań w formacie:");
        System.out.println(" ax^2+bx+c, gdzie a,b,c to parametry, jeżeli brak to wpisz 0");
        for(int i=0;i<param.length;i++){
            param[i]=wpisz.nextDouble();
        }
        double w=param[0]*param[4]-param[1]*param[3];
        double wx=param[2]*param[4]-param[1]*param[5];
        double wy=param[0]*param[5]-param[2]*param[3];
        if(w==0&&wx==0&&wy==0)System.out.println("Układ nieoznaczony");
        else if(w==0&&wx!=0&&wy!=0)System.out.println("Układ sprzeczny");
        else if(w!=0){
            double x=wx/w;
            double y=wy/w;
            System.out.println("Wynik x"+x+" wynik y"+y);
        }
    }
    //Zad09
    public static void getNames(){
        Scanner wpisz= new Scanner(System.in);
        String[] names=new String[9];
        int liczK=0,liczB=0,liczAlfabet=0;
        for(int i=0;i<names.length;i++){
            switch(i%3){
                case 0:
                    System.out.println("Podaj imie:");
                    names[i]=wpisz.next();
                    if(names[i].charAt(names[i].length()-1)=='a')liczK++;
                    break;
                case 1:
                    System.out.println("Podaj nazwisko:");
                    names[i]=wpisz.next();
                    char check=names[i].charAt(0);
                    if(check>='A' && check<='K') liczAlfabet++;
                    break;
                case 2:
                    System.out.println("Podaj miasto:");
                    names[i]=wpisz.next();
                    if(names[i].equals("Bydgoszcz"))liczB++;
                    break;
            }
        }
        System.out.println("Liczniki: a)"+liczK+" b)"+liczAlfabet+" c)"+liczB);
        
    }
    public static void main(String[] args) {
        //randFreq();
        //insertString();
        //insertTab2D();
        //indxEvenOdd();
       // det();
        getNames();
    }
}