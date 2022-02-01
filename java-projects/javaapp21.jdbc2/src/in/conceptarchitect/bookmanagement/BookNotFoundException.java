package in.conceptarchitect.bookmanagement;

public class BookNotFoundException extends RuntimeException {

	private String isbn;

	public BookNotFoundException(String isbn, String message) {
		// TODO Auto-generated constructor stub
		super(message);
		this.isbn=isbn;
	}

	public String getIsbn() {
		return isbn;
	}

}
