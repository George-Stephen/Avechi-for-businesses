package exception;

public class ErrorException extends RuntimeException {
    private final int status;


    public ErrorException(int status,String msg) {
        super(msg);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
