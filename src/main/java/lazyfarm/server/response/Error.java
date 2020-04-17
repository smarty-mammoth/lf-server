package lazyfarm.server.response;

public class Error {
	private String msg;
	private int code;
	
	public Error(int code) {
		this.code = code;		
	}
	
	public String getMsg() { return msg; }
	
	public int getCode() { return code; }
	
	
	
}