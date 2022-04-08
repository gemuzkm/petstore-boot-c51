package com.example.petstorebootc51.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserIsExitsException extends RuntimeException {
    public UserIsExitsException() {
    }

    public UserIsExitsException(String message) {
        super(message);
    }

    public UserIsExitsException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserIsExitsException(Throwable cause) {
        super(cause);
    }

    public UserIsExitsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
