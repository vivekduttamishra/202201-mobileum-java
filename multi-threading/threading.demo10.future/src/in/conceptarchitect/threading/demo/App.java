package in.conceptarchitect.threading.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import in.conceptarchitect.threading.utils.ThreadUtils;

public class App {

	public static void main(String []args) throws InterruptedException {
		
		var executor= Executors.newFixedThreadPool(4);  //we have a set of 4 threads
		
		for(var i=0;i<20;i++) {
			final int id=i;
			executor.execute(()->job(id));			
		}
		
		executor.shutdown(); //no more jobs can be added
		
		executor.awaitTermination(1, TimeUnit.HOURS); //wait for 1 hrs for tasks to be over
		
		System.out.println("Program Ends...");
		
		
		
	}
	
	static void job(int id) {
		
		var random=new Random();
		var workTime=  (random.nextInt(5)+1)*1000;
		
		ThreadUtils.print("job #%d started...\n",id);
		ThreadUtils.sleep(workTime);
		ThreadUtils.print("job#%d finished in %d time\n", id, workTime);
		
		
	}
}
