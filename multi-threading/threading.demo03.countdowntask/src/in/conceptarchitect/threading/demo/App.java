package in.conceptarchitect.threading.demo;

public class App {

	public static void main(String []args) {
		
		var counter1=new CountDownTask(200);
		var counter2=new CountDownTask(20);
		var counter3=new CountDownTask(100);
		
		var thread1=new Thread(counter1);
		var thread2=new Thread(counter2);
		var thread3=new Thread(counter3);
		
		thread1.start();
		thread2.start();
		thread3.start();
		
	}
}
