package com.oc.greenbean.exception;

public class UsernameDuplicatedException extends RuntimeException {

    public UsernameDuplicatedException(String message) {
        super(message);
    }
}
