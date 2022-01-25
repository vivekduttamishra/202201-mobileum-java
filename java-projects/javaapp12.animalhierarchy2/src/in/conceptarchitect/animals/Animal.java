package in.conceptarchitect.animals;

public abstract class Animal {
	
	public abstract String eat(); 
	public abstract String move();
	public abstract String breed();
	
	public String die() {
		return this+" is dead";
	}

}
