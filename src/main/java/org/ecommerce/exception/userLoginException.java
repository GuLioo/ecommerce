package org.ecommerce.exception;

public class userLoginException extends userException{
    public userLoginException(String message) {
        super(message);
    }

    public userLoginException(String message, Throwable cause) {
        super(message, cause);
    }
}
