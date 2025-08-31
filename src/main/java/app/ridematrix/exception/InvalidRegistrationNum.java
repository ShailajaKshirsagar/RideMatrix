package app.ridematrix.exception;

public class InvalidRegistrationNum extends RuntimeException
{
    public InvalidRegistrationNum(String message) {
        super(message);
    }
}
