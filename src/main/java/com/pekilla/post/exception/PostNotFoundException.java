package com.pekilla.post.exception;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException() {
        super("Post not found");
    }
}
