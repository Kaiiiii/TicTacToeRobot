package GameElements.AbstractClasses;

import Exceptions.InvalidMoveException;
import Exceptions.SameMoveException;
import GameElements.Board;

public abstract class Player{
	protected static final double WINNING_SCORE = 100,
								LOSING_SCORE = -100,
								DRAW_SCORE = 0;
	protected char playerChar;

	protected double w1_doubleOwnRow,
					w2_doubleEnemyRow,
					w3_ownFreeRow,
					w4_enemyFreeRow,
					w5_interceptEnemyRow,
					w6_interceptedOwnRow;

	public Player (char playerChar, double w1, double w2, double w3, int w4, int w5, int w6) {
		this.playerChar = playerChar;

		this.w1_doubleOwnRow = w1;
		this.w2_doubleEnemyRow = w2;
		this.w3_ownFreeRow = w3;
		this.w4_enemyFreeRow = w4;
		this.w5_interceptEnemyRow = w5;
		this.w6_interceptedOwnRow = w6;
	}

	public char getChar() {
		return this.playerChar;
	}


	public Board nextMove(Board boardState) throws SameMoveException, InvalidMoveException{
		int[] nextMove = findBestMove(boardState, this);
		int boardDimension = boardState.BOARD_DIMENSIONS;

		if (nextMove[0] < 0 || nextMove[0] > boardDimension || nextMove[1] < 0 || nextMove[1] > boardDimension)
			throw new InvalidMoveException();

		return boardState.move(this, nextMove[0], nextMove[1]);
	}

	protected int[] findBestMove(Board boardState, Player player){
		int boardDimension = boardState.BOARD_DIMENSIONS;
		double highestScore = -Double.MAX_VALUE,
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

	protected double getScore(Board boardState){
		int[] boardAnalysis = analyzeBoard(boardState);

		if (boardState.gameEnded()){
			if (boardState.gameWinner() == this.playerChar){
				return WINNING_SCORE;
			} else if (boardState.gameWinner() == Character.MIN_VALUE){
				return DRAW_SCORE;
			}
			return LOSING_SCORE;
		}

		return this.w1_doubleOwnRow * boardAnalysis[0] +
				this.w2_doubleEnemyRow * boardAnalysis[1] +
				this.w3_ownFreeRow * boardAnalysis[2] +
				this.w4_enemyFreeRow * boardAnalysis[3] +
				this.w5_interceptEnemyRow * boardAnalysis[4] +
				this.w6_interceptedOwnRow * boardAnalysis[5];
	}

	protected int[] analyzeBoard(Board boardState){
		int[] boardScore = {0, 0, 0, 0, 0, 0};
		boardScore = analyzeHorizontal(boardState, boardScore);
		boardScore = analyzeVertical(boardState, boardScore);
		boardScore = analyzeDiagonal(boardState, boardScore);

		return boardScore;
	}

	private int[] analyzeHorizontal(Board boardState, int[] boardScore){
		int[] score = boardScore;
		int boardDimensions = Board.BOARD_DIMENSIONS,
			ownCount,
			enemyCount,
			emptyCount;
		char[][] board = boardState.getGameState();

		for (int i = 0; i < boardDimensions; i++ ) {
			ownCount = 0;
			enemyCount = 0;
			emptyCount = 0;

			for (int j = 0; j < boardDimensions; j++) {
				if (board[i][j] == this.playerChar) ownCount ++;
				else if (board[i][j] == Character.MIN_VALUE) emptyCount ++;
				else enemyCount++;
			}

			score = tallyRow(ownCount, enemyCount, emptyCount,score);
		}
		return score;
	}

	private int[] analyzeVertical(Board boardState, int[] boardScore){
		int[] score = boardScore;
		int boardDimensions = Board.BOARD_DIMENSIONS,
				ownCount,
				enemyCount,
				emptyCount;
		char[][] board = boardState.getGameState();

		for (int i = 0; i < boardDimensions; i++) {
			ownCount = 0;
			enemyCount = 0;
			emptyCount = 0;

			for (int j = 0; j < boardDimensions; j++){
				if (board[j][i] == this.playerChar) ownCount ++;
				else if (board[j][i] == Character.MIN_VALUE) emptyCount ++;
				else enemyCount ++;
			}

			score = tallyRow(ownCount, enemyCount, emptyCount, score);
		}
		return score;
	}

	private int[] analyzeDiagonal(Board boardState, int[] boardScore){
		int[] score = boardScore;
		int boardDimensions = Board.BOARD_DIMENSIONS,
				ownCount = 0,
				enemyCount = 0,
				emptyCount = 0;
		char[][] board = boardState.getGameState();

		for (int i = 0; i < boardDimensions; i++){
			if (board[i][i] == this.playerChar) ownCount ++;
			else if (board[i][i] == Character.MIN_VALUE) emptyCount ++;
			else enemyCount ++;
		}

		score = tallyRow(ownCount, enemyCount, emptyCount, score);
		ownCount = 0;
		enemyCount = 0;
		emptyCount = 0;

		for (int i = 0; i < boardDimensions; i++){
			if (board[i][boardDimensions-i-1] == this.playerChar) ownCount ++;
			else if (board[i][boardDimensions-i-1] == Character.MIN_VALUE) emptyCount ++;
			else enemyCount ++;
		}

		score = tallyRow(ownCount, enemyCount, emptyCount, score);
		return score;
	}

	private int[] tallyRow(int ownCount, int enemyCount, int emptyCount, int[] currScore){
		int[] score = currScore;
		if(ownCount == 1){
			if (emptyCount == 2) score[2] ++;
		} else if (ownCount == 2){
			score[0] ++;
			if (emptyCount == 1) score[2] ++;
			else if (enemyCount == 1) score[5] ++;
		}

		if (enemyCount == 1){
			if (emptyCount == 2) score[3] ++;
		} else if (enemyCount == 2){
			score[1] ++;
			if (emptyCount == 1) score[3] ++;
			else if (ownCount == 1) score[4] ++;
		}

		return score;
	}
}
