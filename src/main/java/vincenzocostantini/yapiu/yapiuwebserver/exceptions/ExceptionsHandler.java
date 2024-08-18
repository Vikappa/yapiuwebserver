package vincenzocostantini.yapiu.yapiuwebserver.exceptions;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vincenzocostantini.yapiu.yapiuwebserver.payload.ErrorResponseDTO.ErrorResponseDTO;


@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(UnauthorizedException.class)
    public ErrorResponseDTO handleUnauthorizedException(UnauthorizedException e) {
        return new ErrorResponseDTO(e.getMessage(), 403);
    }

}
