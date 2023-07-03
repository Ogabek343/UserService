package service.model.constants;

public enum Errors {
    EMAIL_NOT_VALID(400,"Email not valid"),
    PHONE_NUMBER_NOT_VALID(400, "Phone number not valid"),
    WRONG_USER_CREDENTIALS(401,"Wrong user credentials"),
    USER_NOT_FOUND(404, "User not found"),
    CODE_NOT_FOUND(404,"Code not found"),
    CODE_NOT_ACCEPTABLE(406,"Code does not match"),
    USERNAME_TAKEN(409, "Username already taken"),
    USER_ALREADY_CONFIRMED(409,"User already confirmed"),
    CODE_ALREADY_CONFIRMED(409,"Code already confirmed"),
    CODE_EXPIRED(498, "Code expired");

    private final int code;
    private final String message;

    Errors(int code, String message) {
        this.code = code;
        this.message = message;
    }
    @Override
    public String toString() {
        return String.format("Error{ code = %d, message = %s}",code,message);
    }
}
