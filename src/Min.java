import java.util.List;

public class Min implements Operation{
	private int args;
	
	public Min() {
		setArgs(2);
	}
	
	public int getArgs() {
		return args;
	}
	
	public void setArgs(int newArgs) {
		args = newArgs;
	}
	
	public double doOperation(List<Double> arguments) {
		if (arguments.size() == 2) {
			return Math.min(arguments.get(0), arguments.get(1));
		} else {
			double result = Double.MAX_VALUE;
			for (Double argument : arguments) {
				result = Math.min(result, argument);
			}
			return result;
		}
	}
}
