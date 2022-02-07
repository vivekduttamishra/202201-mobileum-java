package in.conceptarchitect.threading.demo;

import in.conceptarchitect.threading.utils.ThreadUtils;

public class Worker {

	Thread thread;
	long workCount;
	Basket basket;
	public Worker(long workCount, Basket basket) {
		super();
		this.workCount = workCount;
		this.basket = basket;
		this.thread=new Thread(this::work);
	}
	
	public void start() {
		thread.start();
	}
	
	public boolean isWorking() {
		return thread.isAlive();
	}
	
	public void await() {
		ThreadUtils.await(thread);
	}
	
	private void work() {
		for(int i=0;i<workCount;i++)
			basket.addItem();
	}
	
	
}
