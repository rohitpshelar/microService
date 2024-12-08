package com.example.user.service.exceptions;

public class ResourceNotFound extends  RuntimeException{

    public ResourceNotFound() {
        super("Not Found");
    }

    public ResourceNotFound(String exception) {
        super(exception);
    }
}
