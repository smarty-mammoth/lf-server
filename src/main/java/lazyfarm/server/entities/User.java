package lazyfarm.server.entities;

public class User {
    private String login;
    private Long id;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    private String hash;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User (String login, String hash) {
        this.login = login;
        this.hash = hash;
    }

    public User(Long id, String login) {
        this.id = id;
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

}
