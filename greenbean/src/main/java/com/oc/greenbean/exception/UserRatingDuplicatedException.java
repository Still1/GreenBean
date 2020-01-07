package com.oc.greenbean.exception;

public class UserRatingDuplicatedException extends RuntimeException {
    public UserRatingDuplicatedException(String message) {
        super(message);
    }
}
