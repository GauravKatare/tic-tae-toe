package sources;

public class Game {
    private int winner,nsize;
    private Player player[]=new Player[2] ;
    
    public Game(int winner, int nsize, Player[] player) {
        this.winner=winner;
        this.nsize=nsize;
        this.player=player;
    }

    public Player[] getPlayer() {
        return player;
    }
    public int getNsize() {
        return nsize;
    }
    public void setNsize(int nsize) {
        this.nsize = nsize;
    }
    public int getWinner() {
        return winner;
    }
    public void setWinner(int winner) {
        this.winner = winner;
    }
    public void setPlayer(Player player[]) {
        this.player = player;
    }
    
}
