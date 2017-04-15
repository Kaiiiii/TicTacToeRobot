package gameelements;

import exceptions.InvalidMoveException;

public class TicTacToeBoard implements Board<char[][]>{
	char[][] board;
	Player firstPlayer, secondPlayer, currPlayer;

	public void newGame(){
		this.board = new char[3][3];

		this.firstPlayer = new TicTacToePlayer('x');
		this.secondPlayer = new TicTacToePlayer('o');
		this.currPlayer = firstPlayer;
	}

	public char[][] getGameState() {
		return this.board;
	}

	public int[] move(int...position){
		try{
			if (position.length!=2){
				throw new InvalidMoveException();
			}

			int row = position[0],
				column = position[1];

			if (row < 0 || row > 3 || column < 0 || column > 3){
				throw new InvalidMoveException();
			}

			this.board[row][column] = currPlayer.getSymbol();
		} catch(Exception e){
			System.out.println(e.getMessage());
		}

		currPlayer = nextPlayer();
		return new int[] {firstPlayer.getScore(), secondPlayer.getScore()};
	}

	private Player nextPlayer(){
		return currPlayer.equals(firstPlayer) ? secondPlayer : firstPlayer;
	}
}
