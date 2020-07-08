package org.ecommerce.exception;

public class userException extends RuntimeException {
    public userException(String message){
        super(message);
    }

    public userException(String message,Throwable cause){
        super(message,cause);
    }

}
