import java.util.*;

public class Main {
	static Console console = new Console();
	static PNConverter converter = new PNConverter();
	static Calculator calc = new Calculator();
	
	public static void main (String[] args) {
		while (true) {
			String[] input = console.getInput();
			if (console.ifQuit == true)
				break;
			
			ArrayList<String> polishNotation = converter.convert(input);
			double result = calc.calculate(polishNotation);
			
			System.out.println(result);
		}

	}
}
 