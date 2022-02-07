package in.conceptarchitect.threading.demo;

public class Basket {

	long items;
	
	public  void addItem() {
		
		synchronized(this) {
			
			var x=items;
			
			x++;
			
			items=x;
		}
		
	}
	
	public long getItems() {return items; }
	
}
