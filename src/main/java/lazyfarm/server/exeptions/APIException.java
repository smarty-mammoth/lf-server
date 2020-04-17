package lazyfarm.server.exeptions;

import lazyfarm.server.response.CodeError;
import lazyfarm.server.response.Error;

public class APIException extends Exception {
    private Error err;
    public APIException(CodeError code) {
        this.err = new Error(code.getValue(), code.toString());
    }

    public Error getError() { return err; }
}
