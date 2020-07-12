package org.ecommerce.exception;

public class ecommerceException extends RuntimeException{
    public ecommerceException(String message) {
        super(message);
    }

    public ecommerceException(String message, Throwable cause) {
        super(message, cause);
    }

}
