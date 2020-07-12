package org.ecommerce.exception;

public class userLogin_passwordError_Exception extends userLoginException{
    public userLogin_passwordError_Exception(String message) {
        super(message);
    }

    public userLogin_passwordError_Exception(String message, Throwable cause) {
        super(message, cause);
    }
}
