package in.conceptarchitect.animals.mammals;

import in.conceptarchitect.animals.Hunter;

public abstract class CatFamily extends Mammal implements Hunter{
	
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
