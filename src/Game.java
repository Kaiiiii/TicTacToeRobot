import Exceptions.SameMoveException;
import GameElements.Board;
import GameElements.DumbPlayer;
import GameElements.SmartPlayer;
import GameElements.AbstractClasses.Player;

public class Game {
	private static Player nextPlayer(Player firstPlayer, Player secondPlayer, Player currPlayer) {
		return currPlayer.equals(firstPlayer) ? secondPlayer : firstPlayer;
	}

	private static Player playGame(Player firstPlayer, Player secondPlayer, Player currPlayer, Board board)
			throws Exception {
		while (!board.gameEnded()) {
			try {
				board = currPlayer.nextMove(board);
				currPlayer = nextPlayer(firstPlayer, secondPlayer, currPlayer);
			} catch (SameMoveException sme) {
				System.out.println(sme.getMessage());
			} catch (Exception e) {
				throw e;
			}
		}

		// printBoard(board.getGameState());
		char winner = board.gameWinner();
		if (winner == firstPlayer.getChar())
			return firstPlayer;
		if (winner == secondPlayer.getChar())
			return secondPlayer;
		return null;
	}

	private static void printBoard(char[][] board) {
		System.out.println("-----------");
		for (int i = 0; i < board.length; i++) {
			System.out.print("|");
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == Character.MIN_VALUE)
					System.out.print(" |");
				else
					System.out.print(Character.toString(board[i][j]) + '|');
			}

			System.out.println();
		}

		System.out.println("-----------");
	}

	public static void main(String[] args) {
		Board board;

		int gamesWon = 0, draws = 0, totalGames = 100;
		char myChar = 'x';

		Player firstPlayer = new SmartPlayer(myChar);
		Player secondPlayer = new DumbPlayer('o');
		Player currPlayer = firstPlayer;
		Player winner;

		for (int i = 0; i < totalGames; i++) {
			board = new Board();
			winner = currPlayer;
			try {
				winner = playGame(firstPlayer, secondPlayer, currPlayer, board);
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}

			if (winner != null) {
				System.out.println("Player " + winner.getChar() + " wins!");
				if (winner.getChar() == myChar) {
					gamesWon++;
				}
			} else {
				System.out.println("It's a draw!");
				draws++;
			}
		}

		System.out.println(">> Total Games: " + totalGames);
		System.out.println(">> Games Won: " + gamesWon);
		System.out.println(">> Draws: " + draws);
	}
}