package com.pekilla.post.constraint;

public interface PostConstraint {
    int TITLE_MIN_LENGTH = 10;
    int TITLE_MAX_LENGTH = 100;

    int CONTENT_MIN_LENGTH = 10;
    int CONTENT_MAX_LENGTH = 5000;

    String TITLE_LENGTH_ERROR = "Title length must be between "+TITLE_MIN_LENGTH+" and "+TITLE_MAX_LENGTH+" characters.";
    String CONTENT_LENGTH_ERROR = "Content length must be between "+CONTENT_MIN_LENGTH+" and "+CONTENT_MAX_LENGTH+" characters.";
}
