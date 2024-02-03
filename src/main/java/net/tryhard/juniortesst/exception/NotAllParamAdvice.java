package net.tryhard.juniortesst.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NotAllParamAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(NotAllParametrsException.class)
    public String handleNotFoundException(NotAllParametrsException notAllParametrsException) {
        return notAllParametrsException.getMessage();
    }
}
