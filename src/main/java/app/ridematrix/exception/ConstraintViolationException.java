package app.ridematrix.exception;

public class ConstraintViolationException extends RuntimeException
{
    public ConstraintViolationException(String message) {
        super(message);
    }
}
