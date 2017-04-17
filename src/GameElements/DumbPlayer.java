package GameElements;

import GameElements.AbstractClasses.Player;

public class DumbPlayer extends Player{
	//Get own double row 
	//Get enemy double row 
	//Get own intercept 
	//Get enemy intercept
	
	/*Get double row
	 * returns [int, int, int, int]
	 * doubleOwnRow, doubleEnemyRow, interceptRow, interceptedRow
	 * count symbol on each row :D :D :D :D :D :D :D  
	*/
	
	public DumbPlayer(char playerChar){
		super(playerChar); 
		this.w1_doubleOwnRow = 0.2;
		this.w2_doubleEnemyRow = 0.4;
		this.w3_interceptRow = 0.2;
		this.w4_interceptedRow = 0.4;
	}
	
	public double getScore(Board boardState){
		int[] boardAnalysis = analyzeBoard(boardState);
		return w1_doubleOwnRow * boardAnalysis[0] + 
				w2_doubleEnemyRow * boardAnalysis[1] + 
				w3_interceptRow * boardAnalysis[2] + 
				w4_interceptedRow * boardAnalysis[3];
	}
	
	
	//TODO: mocked this
	public int[] nextMove(Board boardState) {
		return new int[] {0, 0};
	}
	
	//TODO: mocked this
	private int[] analyzeBoard(Board boardState){
		return new int[] {0, 0, 0, 0};
	}
}
