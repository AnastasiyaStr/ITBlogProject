package home.ua.springbootapp.domain.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class ExceptionResponse {
    private LocalDateTime time;
    private String message;
    private String details;

    public ExceptionResponse(String message,String details){
        this.message = message;
        this.details = details;
        this.time = LocalDateTime.now();
    }
}
