package in.conceptarchitect.maths;

public class Factorial {

	public int calculate(int n) {
		if(n>1)
			return n*calculate(n-1);
		else
			return 1;
	}
}
