
class FactorialApp{

	public static void main(String []args){
		int x= 5;
		int fx= Factorial.calculate(x);
		System.out.println("factorial of "+ x +" is " + fx);
	}
}

class Factorial {
	static int calculate(int n){

		int fn= 1;

		while(n>1){
			return fn*=n--;
		}

		return fn;
	}
}