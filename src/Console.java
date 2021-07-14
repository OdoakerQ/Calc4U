import java.util.*;

public class Console {
	Scanner scan = new Scanner(System.in);
	boolean ifQuit;
		
	public Console() {
		ifQuit = false;
	}
	
	public static boolean checkInput() {
		return true;
	}
		
	public String[] getInput() {
		String input = scan.nextLine();
		boolean isInputGood = false;
		while (!isInputGood) {
			if (input.equals("q") || input.equals("Q")) {
				ifQuit = true;
				return new String[] {"Exit"};
			}
			isInputGood = checkInput();
			if (!isInputGood) {
				System.out.println("Wrong Input.");
			}
		}
		
		String[] newInput = PNConverter.Preper4Conversion(input);
 		return newInput;
	}
}
