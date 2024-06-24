package com.pekilla.exception.type;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException() {
        super("Comment not found ");
    }
}
