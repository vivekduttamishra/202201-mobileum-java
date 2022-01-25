package in.conceptarchitect.animals.mammals;

public class Camel extends Mammal {
	
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

	public String ride() {
		return this+" is a great desert ride";
	}

}
