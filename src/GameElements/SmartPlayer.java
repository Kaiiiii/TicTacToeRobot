package GameElements;

import Exceptions.InvalidMoveException;
import Exceptions.SameMoveException;
import GameElements.AbstractClasses.Player;

public class SmartPlayer extends DumbPlayer{
	private final double modifier = 0.1;
					
	public SmartPlayer (char playerChar){
		super(playerChar);
	}
	
	public Board nextMove(Board boardState) throws SameMoveException, InvalidMoveException{
		char enemyChar = this.playerChar == 'x' ? 'o' : 'x';
		double currentValue = getScore(boardState);
		
		Board nextBoard = super.nextMove(boardState);
		
		double successorValue = predictSuccessorValue(nextBoard, enemyChar);
		updateWeights(successorValue, currentValue, boardState);
		
//		System.out.println("double own row " + w1_doubleOwnRow);
//		System.out.println("double enemy row " + w2_doubleEnemyRow);
//		System.out.println("own free row " + w3_ownFreeRow);
//		System.out.println("enemy free row " + w4_enemyFreeRow);
//		System.out.println("intercept enemy row " + w5_interceptEnemyRow);
//		System.out.println("intercepted own row " + w6_interceptedOwnRow);
//		System.out.println("completed own row " + w7_completedOwnRow);
//		System.out.println("completed enemy row " + w8_completedEnemyRow);
		
		return nextBoard;	
	}

	private double predictSuccessorValue(Board boardState, char enemyChar){
		Board mockBoard = boardState.clone();
		
		Player mockPlayer = new DumbPlayer(enemyChar);
		
		try{ mockPlayer.nextMove(mockBoard); } 
		catch(SameMoveException sme){ return 0; }
		catch(InvalidMoveException ime){ return 0; }
		
//		System.out.println("curr board");
//		printBoard(boardState.getGameState());
//		System.out.println("Mock board");
//		printBoard(mockBoard.getGameState());
		return this.getScore(mockBoard);
	}
	
	public void updateWeights(double trainingValue, double currentScore, Board boardState){
		double blah = this.getScore(boardState);
		double error = trainingValue - blah;
//		System.out.println("training value " + trainingValue);
//		System.out.println("new stuff " + blah);

		int[] boardStateAnalysis = analyzeBoard(boardState);
		
		this.w1_doubleOwnRow += modifier * error * boardStateAnalysis[0];
		this.w2_doubleEnemyRow += modifier * error * boardStateAnalysis[1]; 
		this.w3_ownFreeRow += modifier * error * boardStateAnalysis[2];
		this.w4_enemyFreeRow += modifier * error * boardStateAnalysis[3];
		this.w5_interceptEnemyRow += modifier * error * boardStateAnalysis[4];
		this.w6_interceptedOwnRow += modifier * error * boardStateAnalysis[5];
	}
	
	private void printBoard(char[][] board){
		System.out.println("-----------");
		for (int i = 0; i < board.length; i++){
			System.out.print("|");
			for (int j = 0; j < board[i].length; j++){
				if (board[i][j] == Character.MIN_VALUE) System.out.print(" |");
				else System.out.print(Character.toString(board[i][j]) + '|'); 
			}
			
			System.out.println();
		}
		
		System.out.println("-----------");
	}
}
