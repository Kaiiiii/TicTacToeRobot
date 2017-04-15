package gameelements;

import exceptions.InvalidMoveException;

public class Board{
	char[][] board;

	public Board(){
		this.board = new char[3][3];
	}

	public char[][] getGameState() {
		return this.board;
	}

	public void move(char playerChar, int...position){
		try{
			if (position.length!=2){
				throw new InvalidMoveException();
			}

			int row = position[0],
			column = position[1];

			if (row < 0 || row > 3 || column < 0 || column > 3){
				throw new InvalidMoveException();
			}

			this.board[row][column] = playerChar;

		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	private Player nextPlayer(){
		return currPlayer.equals(firstPlayer) ? secondPlayer : firstPlayer;
	}
}
