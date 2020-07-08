package org.ecommerce.exception;

public class userInsertException extends userException{

    public userInsertException(String message) {
        super(message);
    }

    public userInsertException(String message, Throwable cause) {
        super(message, cause);
    }
}
