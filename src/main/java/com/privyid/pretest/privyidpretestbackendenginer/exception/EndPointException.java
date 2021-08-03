package com.privyid.pretest.privyidpretestbackendenginer.exception;

public class EndPointException extends Exception {

    private static final long serialVersionUID = -7697643370872599673L;

    public EndPointException(String message) {
        super(message);
    }

    public EndPointException(String message, Throwable cause) {
        super(message, cause);
    }
}
