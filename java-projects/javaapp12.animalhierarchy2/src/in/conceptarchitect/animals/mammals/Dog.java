package in.conceptarchitect.animals.mammals;

import in.conceptarchitect.animals.Domestic;
import in.conceptarchitect.animals.Hunter;

public class Dog extends Mammal implements Hunter, Domestic{
	
	@Override	
	public String eat() {
		// TODO Auto-generated method stub
		return this+" is a flesh eater";
	}
	@Override
	public String move() {
		// TODO Auto-generated method stub
		return this+" moves on ground";
	}

	public String hunt() {
		return this+" hunts";
	}

}
