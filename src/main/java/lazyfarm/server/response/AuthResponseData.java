package lazyfarm.server.response;

public class AuthResponseData extends ResponseData {
    private String token;



    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
