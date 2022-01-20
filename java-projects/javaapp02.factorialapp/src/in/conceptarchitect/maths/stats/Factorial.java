package in.conceptarchitect.maths.stats;

public class Factorial {

	public static int calculate(int n) {
		if(n>1)
			return n*calculate(n-1);
		else
			return 1;
	}
}
