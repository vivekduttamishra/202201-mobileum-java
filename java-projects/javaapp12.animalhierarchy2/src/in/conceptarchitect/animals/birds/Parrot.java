package in.conceptarchitect.animals.birds;

import in.conceptarchitect.animals.Domestic;

public class Parrot extends Bird implements Domestic {


	@Override	
	public String eat() {
		// TODO Auto-generated method stub
		return this+" is a fruitarian";
	}
	
	

}
