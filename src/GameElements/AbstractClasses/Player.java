package GameElements.AbstractClasses;

import GameElements.Board;

public abstract class Player{
	protected char playerChar;
	protected int score;
	
	protected double w1_doubleOwnRow, 
						w2_doubleEnemyRow, 
						w3_interceptRow,
						w4_interceptedRow;
	
	public Player (char playerChar) {
		this.playerChar = playerChar;
		this.score = 0;
	}
	
	public abstract double getScore(Board boardState);
	public abstract int[] nextMove(Board boardState);

	public char getChar() {
		return this.playerChar;
	}
}
