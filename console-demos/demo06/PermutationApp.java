class PermutationApp{

	public static void main(String []args){

		int n=5, r =3;

		Permutation p=new Permutation();

		int result=p.calculate(n,r);

		System.out.printf("%d P %d = %d\n",n,r,result);

	}
}