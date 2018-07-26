package pl.altkom.asc.lab.micronaut.poc.policy.shared.exceptions;

import lombok.Getter;

public class BusinessException extends RuntimeException {
    protected static final Object[] EMPTY_ARGS = new Object[0];

    @Getter
    private String code = null;
    @Getter
    private Object[] args = EMPTY_ARGS;

    public BusinessException(String code) {
        super(code);
        this.code = code;
        this.args = EMPTY_ARGS;
    }

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
        this.args = EMPTY_ARGS;
    }

    public BusinessException(String code, String message, Object ... args) {
        super(message);
        this.code = code;
        this.args = args;
    }

    public BusinessException(String code, Object ... args) {
        super(code);
        this.code = code;
        this.args = args;
    }
}
