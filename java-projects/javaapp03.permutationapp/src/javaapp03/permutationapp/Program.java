package javaapp03.permutationapp;

import in.conceptarchitect.maths.Permutation;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int n=5;
		int r=3;
		
		Permutation permutation=new Permutation();
		
		var p= permutation.calculate(n, r);
		
		System.out.printf("%d P %d = %d\n",n,r,p);
		

	}

}
