package exception;

public class CheckException extends RuntimeException{
    private String exceptionClassName;
    private ErrorCode errorCode;
    private String customErrorInfo;

    private <T> void createCheckException(T t, ErrorCode errorCode) {
        this.checkParams(t, errorCode);
        this.exceptionClassName = t.getClass().getName();
        this.errorCode = errorCode;
    }

    private <T> void createCheckException(T t, ErrorCode errorCode, String customErrorInfo) {
        this.checkParams(t, errorCode);
        this.exceptionClassName = t.getClass().getName();
        this.errorCode = errorCode;
        this.customErrorInfo = customErrorInfo;
    }

    public <T> CheckException(T t, ErrorCode errorCode) {
        this.createCheckException(t, errorCode);
    }

    public <T> CheckException(T t, ErrorCode errorCode, String message) {
        super(message);
        this.createCheckException(t, errorCode, message);
    }

    public <T> CheckException(T t, ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.createCheckException(t, errorCode, message + "cause: " + cause.getMessage());
    }

    public <T> CheckException(T t, ErrorCode errorCode, Throwable cause) {
        super(cause);
        this.createCheckException(t, errorCode, cause.getMessage());
    }

    protected <T> CheckException(T t, ErrorCode errorCode, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.createCheckException(t, errorCode, message + "cause: " + cause.getMessage());
    }

    public String getExceptionClassName() {
        return this.exceptionClassName;
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }

    public String getCustomErrorInfo() {
        return this.customErrorInfo;
    }

    private <T> void checkParams(T t, ErrorCode errorCode) {
        if (errorCode == null) {
            throw new NullPointerException("捕获异常时，未提供错误代码和描述");
        } else if (t == null) {
            throw new NullPointerException("捕获异常时，未提供调用类信息");
        }
    }
}
