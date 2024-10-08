package com.blog.blog.exceptions;




import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class GeneralException extends Exception {

    public GeneralException (String message) {
        super(message);
    }

    public GeneralException (String message, Throwable throwable) {
        super(message, throwable);
    }

}