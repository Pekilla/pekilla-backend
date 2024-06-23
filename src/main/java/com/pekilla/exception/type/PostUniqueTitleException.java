package com.pekilla.exception.type;

public class PostUniqueTitleException extends RuntimeException {
    public PostUniqueTitleException(String title, String category) {
        super("The title "+title+" already exists in the category " + category);
    }
}
