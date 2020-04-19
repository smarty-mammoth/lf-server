package lazyfarm.server.response;

public enum CodeError {
    UNKNOWN(0), LOGIN_EXISTS(1), INCORRECT_PASSWORD(2), NOT_AUTHORIZED(3), LOGIN_IS_EMPTY(4), PASSWORD_IS_EMPTY(5);

    private final int value;

    CodeError(int value) {
        this.value = value;
    }

    public int getValue() { return value; }

    @Override public String toString() {

        switch(this) {
            case LOGIN_EXISTS:
                return "Login already exists.";
            case INCORRECT_PASSWORD:
                return "Incorrect password.";
            case NOT_AUTHORIZED:
                return "You're not authorized.";
            case LOGIN_IS_EMPTY:
                return "Login cannot be empty.";
            case PASSWORD_IS_EMPTY:
                return "Password cannot be empty.";
            default: return "Unknown error.";
        }
    }
}
