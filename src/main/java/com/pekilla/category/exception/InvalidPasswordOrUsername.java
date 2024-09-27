package com.pekilla.category.exception;

public class InvalidPasswordOrUsername extends RuntimeException {
    public InvalidPasswordOrUsername() {
        super("Invalid password or username");
    }
}
