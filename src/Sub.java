import java.util.List;

public class Sub implements Operation {	
	private int args;
	
	public Sub() {
		setArgs(2);
	}
	
	public int getArgs() {
		return args;
	}
	
	public void setArgs(int newArgs) {
		args = newArgs;
	}
	
	
	public double doOperation(List<Double> arguments) {
		return arguments.get(0) - arguments.get(1);
	}
}