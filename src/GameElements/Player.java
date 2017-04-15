package gameelements;

public abstract class Player {
	char playerChar;
	int score;

	public abstract int getScore(Board boardState);

	public Player (char playerChar) {
		this.playerChar = playerChar;
		this.score = 0;
	}

	public char getSymbol() {
		return this.playerChar;
	}
}
