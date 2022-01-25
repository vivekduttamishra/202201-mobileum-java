package in.conceptarchitect.animals.birds;

import in.conceptarchitect.animals.Animal;

public class Bird extends Animal {
	@Override
	public String breed() {
		// TODO Auto-generated method stub
		return this+" lays egg";
	}
	@Override
	public String move() {
		// TODO Auto-generated method stub
		return fly();
	}
	public String fly() {
		// TODO Auto-generated method stub
		return this+" flies";
	}
}
