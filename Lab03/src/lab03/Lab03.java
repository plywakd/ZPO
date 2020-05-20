/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab03;

import java.util.Scanner;

/**
 *
 * @author Plywak
 */
public class Lab03 {
    //Zad01
    public static String codePlus4(String text){
        String codedText="";
        for(int i=0;i<text.length();i++){
            char c=text.charAt(i);
            int ascii=(int)c;
            switch(ascii){
                case 32:
                    break;
                case 88: case 89: case 90: case 120: case 121: case 122:
                    ascii-=22;
                    break;
                default:
                    ascii+=4;
                    break;
            }
            codedText+=(char)ascii;
        }
        return codedText;
    }
    public static String codeHash(String text){
        String codedText="";
        for(int i=0;i<text.length();i+=4){
            codedText+=text.charAt(i);
        }
        for(int i=1;i<text.length();i+=2){
            codedText+=text.charAt(i);
        }
        for(int i=2;i<text.length();i+=4){
            codedText+=text.charAt(i);
        }
        return codedText;
    }
    public static void main(String[] args) {
        Scanner odczyt=new Scanner(System.in);
        System.out.println("Podaj text");
        String text=odczyt.nextLine();
        System.out.println("Po kodowaniu:"+codePlus4(text));
        System.out.println("Po kodowaniu:"+codeHash(text));
    }
    
}
