package GameElements;

public class TicTacToePlayer extends Player { 
	public TicTacToePlayer(char playerChar) {
		super(playerChar); 
	}
	
	public int getScore(Board boardState) {
		boardState.getGameState();
		return this.score;
	}
}