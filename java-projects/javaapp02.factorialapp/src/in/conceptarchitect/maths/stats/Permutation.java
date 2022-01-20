package in.conceptarchitect.maths.stats;

public class Permutation {

	public int calculate(int n,int r) {
		return Factorial.calculate(n)/Factorial.calculate(n-r);
	}
}
