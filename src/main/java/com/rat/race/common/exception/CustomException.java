package com.rat.race.common.exception;

import com.rat.race.common.error.ErrorCode;
import lombok.Getter;


@Getter
public class CustomException extends RuntimeException{
    private final ErrorCode errorCode;

    public CustomException(ErrorCode error, String message) {
        super(message);
        this.errorCode = error;
    }

}
