package GameElements;

import java.util.ArrayList;

import Exceptions.InvalidMoveException;
import Exceptions.SameMoveException;
import GameElements.AbstractClasses.Player;

public class Board {
	public static final int BOARD_DIMENSIONS = 3;

	char[][] board;
	ArrayList<Board> gameHistory;

	public Board() {
		this.board = new char[BOARD_DIMENSIONS][BOARD_DIMENSIONS];
		this.gameHistory = new ArrayList<Board>();
	}

	public Board clone() {
		Board newBoard = new Board();
		newBoard.board = copyBoard(this.board, BOARD_DIMENSIONS);

		return newBoard;
	}

	public char[][] getGameState() {
		return this.board;
	}

	public ArrayList<Board> getGameHistory() {
		return this.gameHistory;
	}

	public Board move(Player player, int row, int column) throws SameMoveException, InvalidMoveException {
		if (!isPositionEmpty(row, column))
			throw new SameMoveException(this.board[row][column]);

		if (row < 0 || row > BOARD_DIMENSIONS || column < 0 || column > BOARD_DIMENSIONS)
			throw new InvalidMoveException();

		this.board[row][column] = player.getChar();

		this.gameHistory.add(this);

		return this;
	}

	public char gameWinner() {
		char winnerChar = Character.MIN_VALUE;

		if (!gameEnded())
			return winnerChar;

		char horizontalWinner = horizontalMatches();
		char verticalWinner = verticalMatches();
		char diagonalWinner = diagonalMatches();

		if (horizontalWinner != Character.MIN_VALUE)
			winnerChar = horizontalWinner;
		else if (verticalWinner != Character.MIN_VALUE)
			winnerChar = verticalWinner;
		else if (diagonalWinner != Character.MIN_VALUE)
			winnerChar = diagonalWinner;

		return winnerChar;
	}

	public boolean gameEnded() {
		char horizontalWinner = horizontalMatches();
		char verticalWinner = verticalMatches();
		char diagonalWinner = diagonalMatches();

		if (horizontalWinner != Character.MIN_VALUE)
			return true;
		if (verticalWinner != Character.MIN_VALUE)
			return true;
		if (diagonalWinner != Character.MIN_VALUE)
			return true;
		if (allPositionsFull())
			return true;

		return false;
	}

	private char horizontalMatches() {
		for (int i = 0; i < BOARD_DIMENSIONS; i++) {
			char firstRowChar = this.board[i][0];

			for (int j = 1; j < BOARD_DIMENSIONS; j++) {
				if (firstRowChar != this.board[i][j]) {
					firstRowChar = Character.MIN_VALUE;
					break;
				}
			}

			if (firstRowChar != Character.MIN_VALUE) {
				return firstRowChar;
			}
		}

		return Character.MIN_VALUE;
	}

	private char verticalMatches() {
		for (int i = 0; i < BOARD_DIMENSIONS; i++) {
			char firstColChar = this.board[0][i];

			for (int j = 1; j < BOARD_DIMENSIONS; j++) {
				if (firstColChar != this.board[j][i]) {
					firstColChar = Character.MIN_VALUE;
					break;
				}
			}

			if (firstColChar != Character.MIN_VALUE) {
				return firstColChar;
			}
		}
		return Character.MIN_VALUE;
	}

	private char diagonalMatches() {
		char diagChar = this.board[0][0];
		for (int i = 1; i < BOARD_DIMENSIONS; i++) {
			if (diagChar != this.board[i][i]) {
				diagChar = Character.MIN_VALUE;
				break;
			}
		}

		if (diagChar != Character.MIN_VALUE) {
			return diagChar;
		}

		diagChar = this.board[0][2];
		for (int i = 1; i < BOARD_DIMENSIONS; i++) {
			if (diagChar != this.board[i][BOARD_DIMENSIONS - i - 1]) {
				diagChar = Character.MIN_VALUE;
				break;
			}
		}

		if (diagChar != Character.MIN_VALUE) {
			return diagChar;
		}

		return Character.MIN_VALUE;
	}

	private boolean isPositionEmpty(int row, int column) {
		if (this.board[row][column] != Character.MIN_VALUE)
			return false;
		return true;
	}

	private boolean allPositionsFull() {
		for (int i = 0; i < BOARD_DIMENSIONS; i++) {
			for (int j = 0; j < BOARD_DIMENSIONS; j++) {
				if (this.board[i][j] == Character.MIN_VALUE)
					return false;
			}
		}
		return true;
	}

	private char[][] copyBoard(char[][] original, int dimension) {
		char[][] copy = new char[dimension][dimension];

		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				copy[i][j] = original[i][j];
			}
		}
		return copy;
	}
}
