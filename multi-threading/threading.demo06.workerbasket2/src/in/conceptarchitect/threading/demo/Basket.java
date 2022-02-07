package in.conceptarchitect.threading.demo;

public class Basket {

	long items;
	
	public synchronized void addItem() {
		
		var x=items;
		
		x++;
		
		items=x;
		
	}
	
	public long getItems() {return items; }
	
}
