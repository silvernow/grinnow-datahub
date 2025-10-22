package grinnow.com.cmm.exception;

public class ProcessingException extends Exception{

	private static final long serialVersionUID = 1L;
	private int errorCode; 
	private String message;

	public ProcessingException(String message, int errorCode){
		super(message);
		this.setErrorCode(errorCode);
		this.setMessage(message);
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
