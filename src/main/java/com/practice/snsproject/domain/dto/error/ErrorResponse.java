package com.practice.snsproject.domain.dto.error;

import com.practice.snsproject.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ErrorResponse {
    private ErrorCode errorCode;
    private String message;

    public ErrorResponse(String message){
        this.message = message;
    }

}
