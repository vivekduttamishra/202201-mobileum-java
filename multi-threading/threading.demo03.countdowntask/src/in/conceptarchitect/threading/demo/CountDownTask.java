package in.conceptarchitect.threading.demo;

public class CountDownTask implements Runnable {

	int max;
	public CountDownTask(int max) {
		this.max=max;
	}
	
	
	public void run() {
		
		System.out.printf("[%d] starts\n", Thread.currentThread().getId());
		while(max>=0) {
			
			System.out.printf("[%d] counting %d\n", Thread.currentThread().getId(), max);
			max--;
		}
		
		System.out.printf("[%d] stops\n", Thread.currentThread().getId());

	}

}
