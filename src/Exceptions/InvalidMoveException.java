package Exceptions;

public class InvalidMoveException extends Exception {
	public InvalidMoveException(String...validPositions) {
		super(getErrorMsg(validPositions));
	}
	
	public InvalidMoveException(){
		super("Invalid Move");
	}

	private static String getErrorMsg(String...validPositions) {
		String errorMsg = "---\n\tThat is not a valid move in this board! Valid board positions:";

		for (String position : validPositions) {
			errorMsg = errorMsg.concat("\n\t\t- " + position);
		}
		
		errorMsg = errorMsg.concat("\n---");

		return errorMsg;
	}
}
