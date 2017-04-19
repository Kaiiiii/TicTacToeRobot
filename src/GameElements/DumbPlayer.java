package GameElements;

import Exceptions.SameMoveException;
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
		this.w1_doubleOwnRow = 20;
		this.w2_doubleEnemyRow = -30;
		this.w3_interceptRow = 60;
		this.w4_interceptedRow = -30;
		this.w5_completedOwnRow = 50;
		this.w6_completedEnemyRow = -50;
	}
	
	public int getScore(Board boardState){
		int[] boardAnalysis = analyzeBoard(boardState);
		
		if (boardAnalysis[4] > 0) return w5_completedOwnRow;
		if (boardAnalysis[5] > 0) return w6_completedEnemyRow;
		
//		System.out.print("!!!doubleOwnRow " + boardAnalysis[0] + "\t");
//		System.out.print("!!!doubleEnemyRow " + boardAnalysis[1]+ "\t");
//		System.out.print("!!!interceptRow " + boardAnalysis[2]+ "\t");
//		System.out.print("!!!interceptedRow " + boardAnalysis[3]+ "\t");
//		System.out.print("!!!completedOwnRow " + boardAnalysis[4]+ "\t");
//		System.out.print("!!!completedEnemyRow " + boardAnalysis[5]+ "\t");
//		System.out.println();
		
		return w1_doubleOwnRow * boardAnalysis[0] + 
				w2_doubleEnemyRow * boardAnalysis[1] + 
				w3_interceptRow * boardAnalysis[2] + 
				w4_interceptedRow * boardAnalysis[3];
	}
	
	public int[] nextMove(Board boardState) {
		int boardDimension = boardState.BOARD_DIMENSIONS;
		int highestScore = Integer.MIN_VALUE, 
			mockScore;  
		int[] posOfHighest = {-1, -1};
		
		Board mockBoard;
		
		for (int i = 0; i < boardDimension; i++){
			for (int j = 0; j < boardDimension; j++){
				mockBoard = boardState.clone();
				try{
					mockBoard.move(this, i, j);
				}catch (SameMoveException sme){
					continue;
				}
				mockScore = getScore(mockBoard); 
				if (mockScore > highestScore){
					highestScore = mockScore; 
					posOfHighest[0] = i;
					posOfHighest[1] = j;
				}
			}
		}
		return posOfHighest;
	}
	
	private int[] analyzeBoard(Board boardState){		
		int[] boardScore = {0, 0, 0, 0, 0, 0};
		boardScore = analyzeHorizontal(boardState, boardScore);
		boardScore = analyzeVertical(boardState, boardScore);
		boardScore = analyzeDiagonal(boardState, boardScore);
		
		return boardScore;
	}
	
	private int[] analyzeHorizontal(Board boardState, int[] boardScore){
		int[] score = boardScore;
		int boardDimensions = boardState.BOARD_DIMENSIONS, 
			ownCount, 
			enemyCount;
		char[][] board = boardState.getGameState();
		
		for (int i = 0; i < boardDimensions; i++ ) {
			ownCount = 0; 
			enemyCount = 0;
			
			for (int j = 0; j < boardDimensions; j++) {
				if (board[i][j] == this.playerChar) ownCount ++; 
				else if (board[i][j] != Character.MIN_VALUE) enemyCount ++; 
			}
			
			score = tallyRow(ownCount, enemyCount, score);
		}
		return score;
	}
	
	private int[] analyzeVertical(Board boardState, int[] boardScore){
		int[] score = boardScore;
		int boardDimensions = boardState.BOARD_DIMENSIONS, 
				ownCount, 
				enemyCount;
		char[][] board = boardState.getGameState();
		
		for (int i = 0; i < boardDimensions; i++) {
			ownCount = 0; 
			enemyCount = 0;
			
			for (int j = 0; j < boardDimensions; j++){
				if (board[j][i] == this.playerChar) ownCount ++; 
				else if (board[j][i] != Character.MIN_VALUE) enemyCount ++;
			}
			
			score = tallyRow(ownCount, enemyCount, score);
		}
		return score;
	}
	
	private int[] analyzeDiagonal(Board boardState, int[] boardScore){
		int[] score = boardScore;
		int boardDimensions = boardState.BOARD_DIMENSIONS, 
				ownCount = 0, 
				enemyCount = 0;
		char[][] board = boardState.getGameState();
		
		for (int i = 0; i < boardDimensions; i++){
			if (board[i][i] == this.playerChar) ownCount ++;
			else if (board[i][i] != Character.MIN_VALUE) enemyCount ++;
		}
		
		score = tallyRow(ownCount, enemyCount, score);
		ownCount = 0; 
		enemyCount = 0;
		
		for (int i = 0; i < boardDimensions; i++){
			if (board[i][boardDimensions-i-1] == this.playerChar) ownCount ++;
			else if (board[i][boardDimensions-i-1] != Character.MIN_VALUE) enemyCount ++;
		}
		
		score = tallyRow(ownCount, enemyCount, score);
		return score;
	}
	
	private int[] tallyRow(int ownCount, int enemyCount, int[] currScore){
		int[] score = currScore;
		
		if (ownCount == 2){ 
			score[0] ++;
			
			if (enemyCount == 1) score[3] ++;
		} else if (ownCount == 3) score[4] ++;
		else if (enemyCount == 2){
			score[1] ++;
			
			if (ownCount == 1) score[2] ++;
		} else if (enemyCount == 3) score[5] ++;
		
		return score;
	}
}
