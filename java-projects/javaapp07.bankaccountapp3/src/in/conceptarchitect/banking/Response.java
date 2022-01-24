package in.conceptarchitect.banking;

public class Response {

	private ResponseStatus code;
	private String message;
	
	public ResponseStatus getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}



	public Response(ResponseStatus code, String message) {
		// TODO Auto-generated constructor stub
		this.code=code;
		this.message=message;
	}

}
