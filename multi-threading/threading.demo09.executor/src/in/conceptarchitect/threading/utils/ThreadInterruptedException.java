package in.conceptarchitect.threading.utils;

public class ThreadInterruptedException extends RuntimeException {

	private long id;

	public ThreadInterruptedException(long id, String message, InterruptedException cause) {
		// TODO Auto-generated constructor stub
		this.id=id;
	}

	public long getId() {
		return id;
	}

}
