package in.conceptarchitect.animals.mammals;

public class Cow extends Mammal {
	
	@Override	
	public String eat() {
		// TODO Auto-generated method stub
		return this+" is a grass eater";
	}
	@Override
	public String move() {
		// TODO Auto-generated method stub
		return this+" moves on ground";
	}

	public String provideMilk() {
		return this+" provides milk";
	}

}
