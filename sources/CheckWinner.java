package sources;

public class CheckWinner {
    private char[][] matrix;
    private int nsize;

    CheckWinner(int nsize,char[][] matrix){
        this.nsize=nsize;
        this.matrix=new char [nsize][nsize];
    }

    private int CheckHorizontal(){
        for(int i=0;i<this.nsize;i++){
            int cnt=0;
            for(int j=0;j<this.nsize;j++){
                if(this.matrix[i][j]=='*')
                    cnt++;
                else if(this.matrix[i][j]=='O')
                    cnt--;    
            }
            if(cnt==this.nsize)
                return 1;
            else if(cnt==-1*this.nsize)
                return 2;    
        }
        return 0;
    }

    private int CheckVertical(){
        for(int i=0;i<this.nsize;i++){
            int cnt=0;
            for(int j=0;j<this.nsize;j++){
                if(this.matrix[j][i]=='*')
                    cnt++;
                else if(this.matrix[j][i]=='O')
                    cnt--;    
            }
            if(cnt==this.nsize)
                return 1;
            else if(cnt==-1*this.nsize)
                return 2;   
        }
        return 0;
    }

    private int CheckDiagonal(){
        int diagonal1=0,diagonal2=0;
        for(int i=0;i<this.nsize;i++){
            if(this.matrix[i][i]=='*') {
                diagonal1++;
            } else if(this.matrix[i][i]=='O') {
                diagonal1--;
            } 
            if(this.matrix[i][this.nsize-1-i]=='*'){
                diagonal2++;
            }else if(this.matrix[i][this.nsize-1-i]=='O'){
                diagonal2--;
            }    
        }    
        if(diagonal1==this.nsize||diagonal2==this.nsize)
            return 1;
        else if(diagonal2==-1*this.nsize||diagonal1==-1*this.nsize)
            return 2;    
        return 0;
    }

    public int Result(int n){
        int checkstatus=CheckDiagonal()|CheckHorizontal()|CheckVertical();    
        int unfilledcell=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(this.matrix[i][j]!='_')
                    unfilledcell++;
            }
        }
        if(checkstatus>0)
            return checkstatus;
        if(unfilledcell==n*n)
            return -1;
        return 0;
    }
}

