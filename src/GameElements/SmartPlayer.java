package GameElements;

public class SmartPlayer extends DumbPlayer{
	private final double modifier = 0.1;
	
	public SmartPlayer (char playerChar){
		super(playerChar);
	}
	
	public int[] nextMove(Board boardState){
		double currScore = this.score;
		int[] boardStateAnalysis = analyzeBoard(boardState);
		
		int[] movePos = super.nextMove(boardState);
		double nextScore = this.score;
		
		updateWeights(nextScore, currScore, boardStateAnalysis);
		
		return movePos;
	}
	
	private void updateWeights(double trainingValue, double currBoardScore, int[] boardStateAnalysis){
		double error = trainingValue - currBoardScore;
		this.w1_doubleOwnRow += modifier * error * boardStateAnalysis[0];
		this.w2_doubleEnemyRow += modifier * error * boardStateAnalysis[1]; 
		this.w3_interceptRow += modifier * error * boardStateAnalysis[2];
		this.w4_interceptedRow += modifier * error * boardStateAnalysis[3];
	}
}
