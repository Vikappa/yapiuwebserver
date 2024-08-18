package vincenzocostantini.yapiu.yapiuwebserver.payload.ErrorResponseDTO;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorResponseDTO {

    private String message;
    private LocalDateTime time;

    HttpStatus httpStatus;
    public ErrorResponseDTO(String message,int i) {
        this.time = LocalDateTime.now();
        this.message = message;
        this.httpStatus = HttpStatus.valueOf(i);
    }
}
