package in.conceptarchitect.maths;

public class Permutation {

	public int calculate(int n,int r) {
		var f=new Factorial();
		return f.calculate(n)/f.calculate(n-r);
	}
}
