package GameElements;

import Exceptions.SameMoveException;

public class Board{
	static final int BOARD_DIMENSIONS = 3;
	
	char[][] board;

	public Board(){
		this.board = new char[BOARD_DIMENSIONS][BOARD_DIMENSIONS];
	}

	public char[][] getGameState() {
		return this.board;
	}

	public Board move(Player player, int row, int column) throws SameMoveException{
		if (this.board[row][column] != Character.MIN_VALUE)
			throw new SameMoveException(this.board[row][column]);
		
		this.board[row][column] = player.getChar();
		return this;
	}
	
	public Player gameWinner(Player player1, Player player2){
		if (!gameEnded()) return null;
		
		char horizontalWinner = horizontalMatches();
		char verticalWinner = verticalMatches(); 
		char diagonalWinner = diagonalMatches();
		char winnerChar = Character.MIN_VALUE;
		
		if (horizontalWinner != Character.MIN_VALUE) winnerChar = horizontalWinner;
		else if (verticalWinner != Character.MIN_VALUE) winnerChar = verticalWinner;
		else if (diagonalWinner != Character.MIN_VALUE) winnerChar = diagonalWinner;
		
		if (winnerChar == player1.getChar()) return player1; 
		if (winnerChar == player2.getChar()) return player2;
		
		return null;
	}
	
	public boolean gameEnded(){
		char horizontalWinner = horizontalMatches();
		char verticalWinner = verticalMatches(); 
		char diagonalWinner = diagonalMatches();
		
		if (horizontalWinner != Character.MIN_VALUE) return true;
		if (verticalWinner != Character.MIN_VALUE) return true;
		if (diagonalWinner != Character.MIN_VALUE) return true;
		if (allPositionsFull()) return true;
		
		return false;
	}
	
	private char horizontalMatches() {
		for (int i = 0; i < BOARD_DIMENSIONS; i++ ) {
			char firstRowChar = this.board[i][0];
			
			for (int j = 1; j < BOARD_DIMENSIONS; j++) {
				if(firstRowChar != this.board[i][j]){
					firstRowChar = Character.MIN_VALUE;
					break;
				}
			}
			
			if (firstRowChar != Character.MIN_VALUE){
				return firstRowChar;
			}
		}
		
		return Character.MIN_VALUE;
	}
	
	private char verticalMatches() {
		for (int i = 0; i < BOARD_DIMENSIONS; i++) {
			char firstColChar = this.board[0][i]; 
			
			for (int j = 1; j < BOARD_DIMENSIONS; j++){
				if (firstColChar != this.board[j][i]){
					firstColChar = Character.MIN_VALUE;
					break;
				}
			}
			
			if(firstColChar != Character.MIN_VALUE) {
				return firstColChar;
			}
		}
		return Character.MIN_VALUE;
	}
	
	private char diagonalMatches() {
		char diagChar = this.board[0][0];
		for (int i = 1; i < BOARD_DIMENSIONS; i++) {
			if (diagChar != this.board[i][i]){
				diagChar = Character.MIN_VALUE;
				break;
			}
		}
		
		if (diagChar != Character.MIN_VALUE){
			return diagChar;
		}
		
		diagChar = this.board[0][2];
		for (int i = 1; i < BOARD_DIMENSIONS; i++){
			if (diagChar != this.board[i][BOARD_DIMENSIONS-i-1]){
				diagChar = Character.MIN_VALUE;
				break;
			}
		}
		
		if (diagChar != Character.MIN_VALUE){
			return diagChar;
		}
		
		return Character.MIN_VALUE;
	}
	
	private boolean allPositionsFull(){
		for (int i = 0; i < BOARD_DIMENSIONS; i++){
			for (int j = 0; j < BOARD_DIMENSIONS; j++){
				if (this.board[i][j] == Character.MIN_VALUE) return false;
			}
		}
		return true;
	}
}
