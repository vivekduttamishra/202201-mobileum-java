package in.conceptarchitect.threading.utils;

public class ThreadUtils {

	
	public static void print(String format, Object ...params) {
		
		var message= String.format(format, params);
		
		System.out.printf("[%d] %s",Thread.currentThread().getId(), message);
		
		
		
	}
	
	public static void sleep(int ms) {
		try {
			Thread.sleep(ms);
		}catch(InterruptedException ex) {
			throw new ThreadInterruptedException(Thread.currentThread().getId(), ex.getMessage(),ex);
		}
	}
}
