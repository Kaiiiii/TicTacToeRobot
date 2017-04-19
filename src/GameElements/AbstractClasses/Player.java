package GameElements.AbstractClasses;

import GameElements.Board;

public abstract class Player{
	protected char playerChar;
	protected int score;
	
	protected int w1_doubleOwnRow, 
					w2_doubleEnemyRow, 
					w3_interceptRow,
					w4_interceptedRow, 
					w5_completedOwnRow, 
					w6_completedEnemyRow; 
	
	public Player (char playerChar) {
		this.playerChar = playerChar;
		this.score = 0;
	}
	
	public abstract int getScore(Board boardState);
	public abstract int[] nextMove(Board boardState);

	public char getChar() {
		return this.playerChar;
	}
}
