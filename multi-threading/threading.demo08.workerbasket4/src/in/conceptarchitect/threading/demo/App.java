package in.conceptarchitect.threading.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import in.conceptarchitect.threading.utils.ThreadUtils;

public class App {

	public static void main(String []args) throws InterruptedException {
		
		
		runDemo(1000, 10000, false); //no shared resource
		
		runDemo(1000, 10000, true); //using same shared resource.
		
	}
	
	static void runDemo(int workerCount, int workCount, boolean useSameBasket) {
		
		System.out.printf("Total Workers %d\ttotal work %d\tuseSameBasket %s\texpected total %d\n", workerCount, workCount, useSameBasket, workerCount*workCount);
		
		List<Worker> workers=new ArrayList<Worker>();		
		Basket singleBasket=new Basket();
		
		for(int i=0;i<workerCount;i++) {
			
			Basket basket= useSameBasket? singleBasket : new Basket();
			
			var worker=new Worker(workCount, basket);
			workers.add(worker);		
			
		}
		
		var startTime=System.currentTimeMillis();
		
		//start each worker		
		workers.stream().forEach(worker->worker.start());
		
		
		//let us wait for each worker to finish
		workers.stream().forEach(worker->worker.await());
		
		
		var endTime=System.currentTimeMillis();
		
		
		System.out.println("Total time taken is "+(endTime-startTime));
		
		long totalWorkDone=0;
		
		if(useSameBasket)
			totalWorkDone=singleBasket.getItems();
		else
			totalWorkDone= workers
								.stream()
								.map(worker->worker.basket.getItems())
								.collect(Collectors.summingLong(v->v));
		
		
		System.out.println("Total work done is :"+totalWorkDone);
		
		
		
		
		
		
		
	}


}
