package in.conceptarchitect.threading.demo;

public class App {

	public static void main(String []args) {
		
		var printer= new ThreadInfoPrinter();
		
		var thread=new Thread(printer);
		
		thread.start();  //runs on a new thread
		
		printer.run();   //runs on the current thread (main thread)
		
	}
}
