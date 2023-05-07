package jp.co.axa.apidemo.exception;

public class ObjectNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ObjectNotFoundException() {
        super();
    }
    public ObjectNotFoundException(String msg) {
        super(msg);
    }
}
