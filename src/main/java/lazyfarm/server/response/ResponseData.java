package lazyfarm.server.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ResponseData {
	private boolean success;
	private Error err;
	
	public ResponseData() {
		success = true;		
	}
	
	public boolean getSuccess() {
		return success;
	}

	public void setError(Error err) {
		this.success = false;
		this.err = err;
	}

}