package in.conceptarchitect.threading.demo;

public class ThreadInfoPrinter implements Runnable {

	@Override
	public void run() {
		var currentThread=Thread.currentThread();
		
		System.out.printf("Current  Thrad Id: %d \t Name:%s \t Priority: %d\n", 
									currentThread.getId(),
									currentThread.getName(),
									currentThread.getPriority());

	}

}
