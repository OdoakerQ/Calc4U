import java.util.List;

public class Sqrt implements Operation{
	private int args;
	
	public Sqrt() {
		setArgs(-1);
	}
	
	public int getArgs() {
		return args;
	}
	
	public void setArgs(int newArgs) {
		args = newArgs;
	}
	
	public double doOperation(List<Double> arguments) {
		if (arguments.size() == 1) {
			return Math.sqrt(arguments.get(0));
		} else {
			return Math.pow(arguments.get(0), 1.0 / arguments.get(1));
		}
	}
}
