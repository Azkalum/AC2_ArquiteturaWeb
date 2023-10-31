package github.com.azkalum.controller.advice;

import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class ApiError {

    private final List<String> errors;

    public ApiError(String errorMessage){
        errors = Collections.singletonList(errorMessage);
    }

    public ApiError(List<String> errorMessage){
        errors = errorMessage;
    }

}
