package service.exception;

import service.model.constants.Errors;
import lombok.Getter;

@Getter
public class NotFoundException extends BaseException{
    public NotFoundException(Errors error) {
        super(error);
    }
}
