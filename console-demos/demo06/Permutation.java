class Permutation{

	int calculate(int n, int r){
		System.out.println("Permutation V2"); 
		return Factorial.calculate(n)/ Factorial.calculate(n-r);

	}
}