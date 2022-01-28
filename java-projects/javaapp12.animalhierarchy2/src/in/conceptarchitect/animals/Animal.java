package in.conceptarchitect.animals;

public abstract class Animal {
	
	public abstract String eat(); 
	public abstract String move();
	public abstract String breed();
	
	public String die() {
		return this+" is dead";
	}
	
	public boolean isDomestic() {
		return this instanceof Domestic;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getClass().getSimpleName();
	}

}
