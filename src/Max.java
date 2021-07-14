import java.util.List;

public class Max implements Operation{
	private int args;
	
	public Max() {
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
			return Math.max(arguments.get(0), arguments.get(1));
		} else {
			double result = Double.MIN_VALUE;
			for (Double argument : arguments) {
				result = Math.max(result, argument);
			}
			return result;
		}
	}
}
