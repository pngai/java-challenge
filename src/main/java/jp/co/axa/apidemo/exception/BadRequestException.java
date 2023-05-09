package jp.co.axa.apidemo.exception;

public class BadRequestException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BadRequestException() {
        super();
    }
    public BadRequestException(String msg) {
        super(msg);
    }
}
