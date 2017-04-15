package GameElements;

public class Player{
	char playerChar;
	int score;

	public Player (char playerChar) {
		this.playerChar = playerChar;
		this.score = 0;
	}

	public int getScore(Board boardState) {
		boardState.getGameState();
		return this.score;
	}

	public char getChar() {
		return this.playerChar;
	}
}
