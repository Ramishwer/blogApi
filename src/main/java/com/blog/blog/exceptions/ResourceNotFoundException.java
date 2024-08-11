package com.blog.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{

    String resourceName;
    String fieldName;
    Integer resourceValue;

    public ResourceNotFoundException(String resourceName, String fieldName, Integer resourceValue) {
        super(String.format("%s not found with %s : %d",resourceName,fieldName,resourceValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.resourceValue = resourceValue;
    }
}
