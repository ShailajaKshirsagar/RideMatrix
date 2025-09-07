package app.ridematrix.exception;

public class MissingServletRequestParameterException extends RuntimeException
{
    public MissingServletRequestParameterException(String message) {
        super(message);
    }
}
