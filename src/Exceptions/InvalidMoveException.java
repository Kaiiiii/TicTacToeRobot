package exceptions;

public class InvalidMoveException extends Exception {
	public InvalidMoveException(String...validPositions) {
		super(getErrorMsg(validPositions));
	}

	private static String getErrorMsg(String... validPositions) {
		String errorMsg = "That is not a valid move in this board! Valid board positions: \n";

		for (String position : validPositions) {
			errorMsg.concat("\t-" + position + "\n");
		}

		return errorMsg;
	}
}
