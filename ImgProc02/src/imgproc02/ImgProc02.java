/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imgproc02;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class ImgProc02 {

    static{
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    public static void main(String[] args) throws IOException {
        Imgcodecs imageCodecs = new Imgcodecs();
        Mat img=imageCodecs.imread("C:\\Users\\Plywak\\Pictures\\DW.jpg"); //Wczytanie zdjecia do pamieci, 2 oznacza kod imread GRAYSCALE - odcienie szarosci
        System.out.println("Loaded");//Potwierdznie wczytania
        Imgproc.circle(//Kolko z parametrami obiektu, punkt startujacy, promien, kolor i grubosc
                img,new Point(80,60),50,new Scalar(0,0,255),10);
        Imgproc.rectangle(//Kwadrat, parametr obiektu, punkt startowy, punkt po przekątnej,kolor i grubosc
                img,new Point(100,200),new Point(150,300),new Scalar(0,100,0),5);
        Imgproc.line(//Linia, parametr obiektu, punkt startowy, punkt koncowy, kolor i grubosc
                img,new Point(150,400),new Point(250,450),new Scalar(255,255,0),5);
        MatOfByte matOfByte = new MatOfByte();//Mapa kodujaca zdjecie
        Imgcodecs.imencode(".jpg",img,matOfByte);//zakodowanie zdjecia
        byte[] byteArray=matOfByte.toArray();//zapisanie zakodowanego zdjecia w tablicy
        InputStream in = new ByteArrayInputStream(byteArray);//przygotowanie zbuforowanego zdjecia do wyswietlenia
        BufferedImage buffImg = ImageIO.read(in);
        JFrame frame= new JFrame();//ramka na wyswietlenie zdjecia
        frame.getContentPane().add(new JLabel(new ImageIcon(buffImg)));//Wpisane zawartosci do ramki
        frame.pack();
        frame.setVisible(true);//Wyswietlenie pelnej zaladowanej 
    }
}