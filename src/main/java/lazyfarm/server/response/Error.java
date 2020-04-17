package lazyfarm.server.response;

public class Error {
	private String msg;
	private int code;
	
	public Error(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public String getMsg() { return msg; }
	
	public int getCode() { return code; }
	
	
	
}