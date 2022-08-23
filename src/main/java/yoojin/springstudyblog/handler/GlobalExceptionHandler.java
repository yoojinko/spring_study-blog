package yoojin.springstudyblog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice //excpetion 발생 시 이 함수를 실행시키려면 필요
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value=Exception.class)
    public String handleArgumentException(IllegalArgumentException e) {
        return "<h1>"+e.getMessage()+"</h>";
    }
}
