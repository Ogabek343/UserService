package service.exception;

import service.model.constants.Errors;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    private final Errors error;
    public BaseException(Errors error) {
        super(error.toString());
        this.error = error;
    }
}
