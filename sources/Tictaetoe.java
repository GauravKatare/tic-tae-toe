package sources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Tictaetoe implements Runnable{
    private List<Game> games= new ArrayList<Game>();
    private int currturn,winner,nsize;
    private Player player[];
    private char[][] matrix = new char [3][3];
    BufferedReader br;

    Tictaetoe(){
        this.player=new Player[3];
        this.matrix=new char [3][3];
    }

    private void reset(){
        this.winner=-1;
        this.currturn=-1;
        for(int i=0;i<=2;i++){
            for(int j=0;j<=2;j++)
                this.matrix[i][j]='_';
        }
        System.out.println("Enter -1 for reset the game");
        System.out.println("Who wants to play first?");
        System.out.println("Press 0 for "+this.player[0].getName());
        System.out.println("Press 1 for "+this.player[1].getName());
    }

    private void display(){
        System.out.println(this.player[this.currturn].getName()+" turns");
        for(int i=0;i<=2;i++)
            System.out.println(this.matrix[i]);
    }

    private void error(){
        System.out.println("wrong move");
    }
    public void run(){
        BufferedReader reader =new BufferedReader(new InputStreamReader(System.in));
        Tictaetoe tictaetoe=new Tictaetoe();
        String name1="player1",name2="player2";
        try {
            System.out.println("Enter your name of Player 1 ");
            name1 = reader.readLine();
            System.out.println("Enter your name of Player 2 ");
            name2 = reader.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        tictaetoe.player[0]=new Player(name1);
        tictaetoe.player[1]=new Player(name2);
        tictaetoe.player[2]=new Player("Draw");
        System.out.println("Enter size of matrix");
        try {
            tictaetoe.nsize=Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        tictaetoe.reset();
        while(true){
            while(tictaetoe.currturn==-1){
                try {
                    tictaetoe.currturn=Integer.parseInt(reader.readLine());
                } catch (NumberFormatException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if(tictaetoe.currturn!=0&&tictaetoe.currturn!=1){
                    System.out.println("Sorry wrong input");
                }
            }
            tictaetoe.display();
            int x=-2;
            System.out.println("Enter x:");
            try {
                x = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if(x==-1){
                tictaetoe.reset();
                continue;
            }
            int y=-1;
            System.out.println("Enter y:");
            try {
                y = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if(tictaetoe.move(x,y)==1)
                tictaetoe.currturn=(tictaetoe.currturn+1)%2;
            if(tictaetoe.checkwinner(tictaetoe.nsize)==1){
                if(tictaetoe.winner==-1)
                    tictaetoe.winner=2;
                Game game=new Game(tictaetoe.winner,tictaetoe.nsize,tictaetoe.player);
                games.add(game);
                if(tictaetoe.winner!=2)
                    System.out.println(tictaetoe.player[tictaetoe.winner].getName()+"is winner");
                else
                    System.out.println("Its a Draw"+tictaetoe.player[tictaetoe.winner].getName());
                System.out.println("Enter 1 for play again");
                int a=-1;
                try {
                    a = Integer.parseInt(reader.readLine());
                } catch (NumberFormatException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if(a==1){
                    tictaetoe.reset();
                    continue;
                }
                System.out.println("Dashboard");
                for(Game iterator :games){
                    Player[] player=iterator.getPlayer();
                    System.out.println("Player 1: "+player[0].getName());
                    System.out.println("Player 2: "+player[1].getName());
                    System.out.println(iterator.getNsize());
                    System.out.println(iterator.getWinner());
                }
                break;
            }    
        }
    }

    private int move(int x,int y) {
        if(x<0||x>=this.nsize||y<0||y>=this.nsize||this.matrix[x][y] != '_'){
            this.error();
            return 0;
        }
        if(this.currturn==0){
            this.matrix[x][y]='O';
        }
        else{
            this.matrix[x][y]='*';
        }
        return 1;
    }

    private int checkwinner(int n){
        // Horizontal Row
        for(int i=0;i<n;i++){
            int cnt=0;
            for(int j=0;j<n;j++){
                if(this.matrix[i][j]=='*')
                    cnt++;
                else if(this.matrix[i][j]=='O')
                    cnt--;    
            }
            if(cnt==n||cnt==-1*n){
                this.winner=1-this.currturn;
                return 1;
            }    
        }  
        // Verical Column
        for(int i=0;i<n;i++){
            int cnt=0;
            for(int j=0;j<n;j++){
                if(this.matrix[j][i]=='*')
                    cnt++;
                else if(this.matrix[j][i]=='O')
                    cnt--;    
            }
            if(cnt==n||cnt==-1*n){
                this.winner=1-this.currturn;
                return 1;
            }    
        }
        // two diagonal
        int diagonal1=0,diagonal2=0,unfilledcell=0;
        for(int i=0;i<n;i++){
            if(this.matrix[i][i]=='*') {
                diagonal1++;
            } else if(this.matrix[i][i]=='O') {
                diagonal1--;
            } 
            if(this.matrix[i][n-1-i]=='*'){
                diagonal2++;
            }else if(this.matrix[i][n-1-i]=='O'){
                diagonal2--;
            }    
        }    
        if(diagonal1==n||diagonal1==-1*n||diagonal2==n||diagonal2==-1*n){
            this.winner=1-this.currturn;
            return 1;
        }    
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(this.matrix[i][j]!='_')
                    unfilledcell++;
            }
        }
        if(unfilledcell==n*n)
            return 1;
        return 0;
    }
}
