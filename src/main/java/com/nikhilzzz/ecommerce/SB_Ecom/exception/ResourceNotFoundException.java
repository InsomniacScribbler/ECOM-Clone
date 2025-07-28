package com.nikhilzzz.ecommerce.SB_Ecom.exception;

public class ResourceNotFoundException extends RuntimeException{
    String resourceName;
    String field;
    String fieldName;
    Long fieldId;

    ResourceNotFoundException(){}

    ResourceNotFoundException(String resourceName, String field, String fieldName) {
        super(String.format("%s resource in %s field not found", resourceName, field));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldName = fieldName;
    }
    ResourceNotFoundException(String resourceName, String field,  Long fieldId) {
        super(String.format("%s resource in %s field not found", resourceName, field));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldId = fieldId;
    }

}
