package in.conceptarchitect.utils.tests;

import in.conceptarchitect.utils.Input;

public class TestInput {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Input input=new Input();
		
		String name=input.readString("Hey whats your name?");
		int number=input.readInt("Whats your lucky number?");
		
		System.out.println(name);
		System.out.println(number);
	}
}
