package by.bsuir.spp.exceptions;

public class DbException extends Exception {

    public DbException(String message) {
        super(message);
    }

    public DbException() {
    }

    public DbException(String message, Throwable cause) {
        super(message, cause);
    }

    public DbException(Throwable cause) {
        super(cause);
    }
}
