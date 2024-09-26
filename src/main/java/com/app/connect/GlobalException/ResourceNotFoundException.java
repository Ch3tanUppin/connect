package com.app.connect.GlobalException;

public class ResourceNotFoundException extends RuntimeException{
    String resourceName;
    String field;
    String fieldName;
    long fieldId;

    //constructor
    public ResourceNotFoundException(String resourceName, String field, String fieldName) {
        super(String.format("%s not found %s:%s", resourceName, field, fieldName));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldName = fieldName;
    }
    public ResourceNotFoundException(String resourceName, String field, long fieldId) {
        super(String.format("%s not found %s:%d", resourceName, field, fieldId));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldId = fieldId;
    }

    

}

