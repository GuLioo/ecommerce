package org.ecommerce.exception;

public class userLoginException extends ecommerceException{
    public userLoginException(String message) {
        super(message);
    }

    public userLoginException(String message, Throwable cause) {
        super(message, cause);
    }
}
