package org.ecommerce.exception;

public class userLogin_NoUser_Exception extends userLoginException{
    public userLogin_NoUser_Exception(String message) {
        super(message);
    }

    public userLogin_NoUser_Exception(String message, Throwable cause) {
        super(message, cause);
    }
}
