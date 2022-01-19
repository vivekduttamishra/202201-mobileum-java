
class FactorialApp{

	public static void main(String []args){
		int x= 5;
		int fx= factorial(x);
		System.out.println("factorial of "+ x +" is " + fx);
	}

	static int factorial(int n){

		int fn= 1;

		while(n>1){
			return fn*=n--;
		}

		return fn;
	}
}