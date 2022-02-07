package in.conceptarchitect.threading.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import in.conceptarchitect.threading.utils.ThreadUtils;

public class App {

	public static void main(String []args) throws InterruptedException, ExecutionException {
		
		var executor= Executors.newFixedThreadPool(2);  //we have a set of 4 threads
		
		int n=7;
		int r=3;
		
		var start=System.currentTimeMillis();
		
		var fn= executor.submit(()-> factorial(n));   //7 seconds
		
		var fn_r= executor.submit(()->factorial(n-r)); //4 seconds (in parallel)
		
		
		System.out.println("please wait while we get the result...");
		
		while(! fn.isDone() || ! fn_r.isDone()) {
			System.out.print(" . ");
			ThreadUtils.sleep(200);
		}
		
		
		var result=fn.get()/fn_r.get();
		
		var end=System.currentTimeMillis();
		System.out.println("\npermutation is "+result);
		System.out.println("Total time taken is "+(end-start));
		
	}
	
	static int factorial(int number) {
		
		int fx=1;
		for(int i=1;i<=number;i++) {
			ThreadUtils.sleep(1000); //represents a long running task
			fx*=i;
		}
		
		return fx;
		
	}
}
