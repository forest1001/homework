package cz.forest.homework.exception;

public class HomeworkBaseRuntimeException extends RuntimeException {
    public HomeworkBaseRuntimeException() {
        super();
    }

    public HomeworkBaseRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public HomeworkBaseRuntimeException(Throwable cause) {
        super(cause);
    }

    protected HomeworkBaseRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public HomeworkBaseRuntimeException(String message) {
        super(message);
    }
}
