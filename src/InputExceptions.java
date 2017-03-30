public class InputExceptions {
	
	class InvalidEntryException extends Exception {
		public InvalidEntryException() {
			super("Invalid position chosen!\n"
					+ "Valid Columns: 1 - 3\n"
					+ "Valid Rows: 1 - 3");
		}
	}
}
