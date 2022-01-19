class Factorial {
	static int calculate(int n){

		int fn= 1;

		while(n>1){
			return fn*=n--;
		}

		return fn;
	}
}