package in.conceptarchitect.animals.reptiles;

import in.conceptarchitect.animals.Animal;
import in.conceptarchitect.animals.Hunter;

public abstract class Reptile extends Animal implements Hunter {

	@Override
	public String breed() {
		// TODO Auto-generated method stub
		 return this+" lays eggs";
	}
	
	@Override
	public String eat() {
		// TODO Auto-generated method stub
		return this+" is a flesh eater";
	}
	
	@Override
	public String move() {
		// TODO Auto-generated method stub
		return this+" crawls";
	}
	
	
	
}
