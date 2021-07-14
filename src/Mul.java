import java.util.List;

public class Mul implements Operation {	
	private int args;
	
	public Mul() {
		setArgs(2);
	}
	
	public int getArgs() {
		return args;
	}
	
	public void setArgs(int newArgs) {
		args = newArgs;
	}
	
	public double doOperation(List<Double> arguments) {
		return arguments.get(0) * arguments.get(1);
	}
}