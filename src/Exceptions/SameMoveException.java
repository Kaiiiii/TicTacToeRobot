package Exceptions;

public class SameMoveException extends Exception {
	public SameMoveException(char existingPlayer) {
		super(getErrorMsg(existingPlayer));
	}

	private static String getErrorMsg(char existingPlayer) {
		return "---\n\tPlayer " + Character.toString(existingPlayer) + " is already at this position!\n---";
	}
}
