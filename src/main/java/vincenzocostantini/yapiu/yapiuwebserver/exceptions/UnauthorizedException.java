package vincenzocostantini.yapiu.yapiuwebserver.exceptions;


public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}