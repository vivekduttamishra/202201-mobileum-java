package in.conceptarchitect.threading.demo;

public class App {

	public static void main(String []args) {
		
		var counter1=new CountDownThread(200);
		var counter2=new CountDownThread(20);
		var counter3=new CountDownThread(100);
		
		//since counters are Threads we don't need separate threads
		
		counter1.start();
		counter2.start();
		counter3.start();
		
	}
}
