package service.exception;

import service.model.constants.Errors;
import lombok.Getter;

@Getter
public class BadRequestException extends BaseException{
    public BadRequestException(Errors error) {
        super(error);
    }
}
