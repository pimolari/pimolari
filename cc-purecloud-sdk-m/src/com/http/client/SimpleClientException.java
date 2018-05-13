package com.http.client;

/**
 * Wraps multiple non-recoverable exceptions that clients can throw
 */
public class SimpleClientException extends Exception {

    private static final long serialVersionUID = 5824384900893852255L;

    /**
     * Exception with message
     * @param message Descriptive explanation of the exception
     */
    public SimpleClientException(String message){
        super(message);
    }
    
    /**
     * Exception with cause
     * @param cause Parent exception that caused this exception
     */
    public SimpleClientException(Throwable cause){
        super(cause);
    }
    
    /**
     * Exception with message and cause
     * @param message Descriptive explanation of the exception
     * @param cause Parent exception that caused this exception
     */
    public SimpleClientException(String message, Throwable cause){
        super(message, cause);
    }
}
