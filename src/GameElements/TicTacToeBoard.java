package GameElements;
import InputExceptions;
import InputExceptions.InvalidEntryException;

public class TicTacToeBoard {
	private char[][] gameTable;
	
	InputExceptions exceptions = new InputExceptions();
	
	public void newGame(){
		gameTable = new char[3][3];
	}
	
	public char[][] getGameState() {
		return gameTable;
	}
	
	public char[][] move(int row, int column, char input) throws InputExceptions.InvalidEntryException { 
		if (row < 0 || row > 3 || column < 0 || column > 3){
			throw exceptions.new InvalidEntryException();
		}
		
		gameTable[row][column] = input;
		
		return gameTable; 
	}
}
	