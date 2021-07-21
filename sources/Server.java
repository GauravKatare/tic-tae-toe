package sources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Start {
    public static void main(String[] args) throws NumberFormatException, IOException{
        BufferedReader reader =new BufferedReader(new InputStreamReader(System.in));
        int noofgame=Integer.parseInt(reader.readLine());
        Tictaetoe[] tictaetoe=new Tictaetoe[noofgame];
        Thread[] t=new Thread[noofgame];
        for(int i=0;i<noofgame;i++){
            tictaetoe[i]=new Tictaetoe();
            t[i]=new Thread(tictaetoe[i]);
            t[i].start();
        }
    }
}
