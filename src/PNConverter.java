import java.util.*;
import com.cal4u.exceptions.FunctionNameException;

public class PNConverter {
	static final Map<String, Operator> allOperators = Map.of(
		 "+", new Operator("left", 2),
		 "-", new Operator("left", 2),
		 "*", new Operator("left", 5),
		 "/", new Operator("left", 5)
		 );
	
	static final Map<String, Operator> allFunctions = Map.of(
			 "max", new Operator("none", 1),
			 "min", new Operator("none", 1),
			 "sqrt", new Operator("none", 1)
			 );
	
	public ArrayList<String> convert(String[] tokens) {
		Stack<String> operators = new Stack<String>();
		ArrayList<String> result = new ArrayList<String>();
		operators.add("#");
		for (String token : tokens) {
			if (isNumber(token)) {
				result.add(token);
			} else if (isFunction(token)) {
				operators.add(token);
			} else if (isOperator(token)) {
				if (operators.peek() != "#")
					while ((operators.peek() != "(" && 
							checkValuesOfTokens(operators.peek(), token, ">")) ||
									(checkValuesOfTokens(operators.peek(), token, "==")
											&& allOperators.get(token).associative == "left")) {
						result.add(operators.pop());
						if (operators.peek() == "#")
							break;
					}
				operators.push(token);
				
			} else if (token.equals("(")) {
				operators.add(token);
			} else if (token.equals(")")) {
				while (!operators.peek().equals("(")) {
					result.add(operators.pop());
				}
				operators.pop();
					if (isFunction(operators.peek())) {
						result.add(operators.pop());
					}
			}
		}
		
		while (operators.peek() != "#") {
			result.add(operators.pop());
		}
		return result;
	}
	
	public static String[] Preper4Conversion(String input) {
		final String[] leftOperators = { "+", "-", "*", "/" , ","};
		final String[] nonOperators = { "(", ")" };
		final String[] functions = { "max", "min", "sqrt" };
		List<String> splitedInput = Arrays.asList(input.split(" +"));
		List<String> newInput = new ArrayList<String>();
		List<String> newWords = new ArrayList<String>();
		int wonderingIdx = 0;
		boolean wasAdded;
		int passingArguments = 0;
		
		for (String word : splitedInput) {
			wonderingIdx = 0;
			int wordLen = word.length();
			newWords.removeAll(newWords);
			for (int i = 0; i < wordLen; i++) {
				wasAdded = false;
				for (String operator : leftOperators) {
					if (operator.equals(Character.toString(word.charAt(i)))) {
						if (wordLen == 1) {
							newWords.add(operator);
							
						} else if (operator == ",") {
							if (i == wonderingIdx) {
								wonderingIdx = i + 1;
							} else {
								newWords.add(word.substring(wonderingIdx, i));
								wonderingIdx = i + 1;
							}
							
						} else if (i == wonderingIdx) {
							newWords.add(operator);
							wonderingIdx = i + 1;
							
						} else {
							newWords.add(word.substring(wonderingIdx, i));
 							newWords.add(operator);
							wonderingIdx = i + 1;

						}
						wasAdded = true;
						break;
					}
				}
				if (wasAdded) continue;
				
				for (String operator : nonOperators) {
					if (operator.equals(Character.toString(word.charAt(i)))) {
						wasAdded = true;
						if (operator == "(") {
							if (passingArguments > 0) {
								newWords.add(operator);
								wonderingIdx = i + 1;
							} else {
								newWords.add(operator);
								wonderingIdx = i + 1;
							}
							
						} else if (operator == ")") {
							if (wordLen == 1){
								newWords.add(operator);
							} else if (i == wordLen &&
								Character.toString(word.charAt(wordLen - 1)).equals(")")) {
								newWords.add(word.substring(wonderingIdx, i));
								newWords.add(operator);
								
							} else if (passingArguments > 0) {
								newWords.add(word.substring(wonderingIdx, i));
								newWords.add(operator);
								wonderingIdx = i + 1;
								passingArguments--;
							} else {
								newWords.add(word.substring(wonderingIdx++, i));
								newWords.add(operator);
								wonderingIdx++;
								i = wonderingIdx + 1;
								newWords.add(word.substring(wonderingIdx++, i));
								
							}
						}
						break;
						
					}
				}
				
				if (wasAdded) continue;
				
				if (isLetter(word.charAt(i))) {
					for (String function : functions) {
						for (int j = i; j < wordLen + 1; j++) {
							if (word.substring(wonderingIdx, j).equals(function)) {
								newWords.add(word.substring(wonderingIdx, j));
								i += function.length() - 1;
								wonderingIdx = i + 1;
								wasAdded = true;
								passingArguments++;
								break;
								
							}
						}
						if (wasAdded) break;
					}
					if (!wasAdded)
						throw new FunctionNameException("Wrong function name");
				}
				
			}
			if (isNumber(Character.toString(word.charAt(wordLen - 1))))
				newWords.add(word.substring(wonderingIdx, wordLen));
				
			newInput.addAll(newWords);
		}
		
		return newInput.toArray(new String[0]);
	}

	public static boolean isLetter(char chr) {
		return chr > 96 && chr < 123;
	}
	
	public static boolean isNumber(String token) {
		for (Character chr : token.toCharArray()) {
			if ((chr < 48 || chr > 57) && chr != '.')
				return false;
		}
		
		return true;

	}
	
	public static boolean isFunction(String token) {
		for (Map.Entry<String, Operator> function : allFunctions.entrySet()) {
			if (function.getKey().equals(token))
				return true;
			
		}
		return false;
				
	}
	
	public static boolean isOperator(String token) {
		for (Map.Entry<String, Operator> operator : allOperators.entrySet()) {
			if (operator.getKey().equals(token))
				return true;
		}
		
		return false;
	}
	
	public static boolean isParenthesis(String token) {
		return token != ")" && token != "(";
	}
	
	public static boolean checkValuesOfTokens(String firstToken, String secondToken, String operator) {
		if (firstToken.equals("(") || firstToken.equals(")"))
			return false;
		if (operator == ">") {
			return allOperators.get(firstToken).value > allOperators.get(secondToken).value;
		} else if (operator == "==") {
			return allOperators.get(firstToken).value == allOperators.get(secondToken).value;
		} else {
			return false;
		}
	}
}