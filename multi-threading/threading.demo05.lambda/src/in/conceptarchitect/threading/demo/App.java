package in.conceptarchitect.threading.demo;

import in.conceptarchitect.threading.utils.ThreadUtils;

public class App {

	public static void main(String []args) throws InterruptedException {
		
		var thread1=new Thread(()->countDown(200));
		var thread2=new Thread(()->countDown(100));
		var thread3=new Thread(App::quickCountDown);
		
		thread1.start();
		thread2.start();
		thread3.start();
		
		//other threads count down 350 together
		//they run in parallel
		
		//busyMain();
		
		//checkAlive(thread1, thread2, thread3);
		
		//ThreadUtils.sleep(5000) ; //sleep for 5 seconds
		
		thread1.join();
		thread2.join();
		thread3.join();
		
		
		ThreadUtils.print("Main Ends\n");
		
		
	}


	private static void checkAlive(Thread thread1, Thread thread2, Thread thread3) {
		while(thread1.isAlive() || thread2.isAlive() || thread3.isAlive())
			System.out.print("+");
	}


	private static void busyMain() {
		//let main count 350 alone
		
		for(var i=0;i<40000;i++)
			;
	}
	
	
	static void quickCountDown() {
		countDown(50);
	}
	
	static void countDown(int max) {
		
		ThreadUtils.print("started\n");
		
		while(max>=0) {
			ThreadUtils.print("counts %d\n",max);
			max--;
		}
		
		ThreadUtils.print("ends\n");
		
	}
}
