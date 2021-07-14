import java.util.*;

public class Calculator {
	static final Map<String, Operation> operations = Map.of(
			 "+", new Add(),
			 "-", new Sub(),
			 "*", new Mul(),
			 "/", new Div(),
			 "max", new Max(),
			 "min", new Min(),
			 "sqrt", new Sqrt()
			 );
	
	public static double getResult(String operation, List<Double> args) {
		int argsLength = operations.get(operation).getArgs(); 
		if (args.size() == argsLength || argsLength == -1) {
			return operations.get(operation).doOperation(args);
		} else {
			throw new IllegalArgumentException("Wrong number of arguments");
		}
	}
	
	public double calculate(ArrayList<String> polishNotation) {
		double result = 0;
		List<Double> args = new ArrayList<Double>();
		String operation;	
		Stack<Double> stack = new Stack<Double>();
		
		for (int i = 0; i < polishNotation.size(); i++) {
			if (PNConverter.isOperator(polishNotation.get(i)) || 
					PNConverter.isFunction(polishNotation.get(i))) {
				operation = polishNotation.get(i);
				int len = operations.get(operation).getArgs();
				if (len == -1) {
					while (!stack.empty()) {
						args.add(stack.pop());
					}
				} else {
					for (int j = 0 ; j < len ; j++) {
						args.add(stack.pop());
					}
				}

				Collections.reverse(args);
				result = getResult(operation, args);
				stack.push(result);
				args.clear();
			} else {
				stack.push(Double.parseDouble(polishNotation.get(i)));
			}
		}
		
		return result;
	}
}
