package github.com.azkalum.controller.advice;

import github.com.azkalum.exception.ProfessorUnavailableException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;


@RestControllerAdvice
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ApplicationControllerAdvice {


    @ExceptionHandler(ProfessorUnavailableException.class)
    public String handleProfessorUnavailableException(ProfessorUnavailableException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handlerMethodValidException(jakarta.validation.ConstraintViolationException ex) {
        List<String> erros = ex.getConstraintViolations()
                .stream()
                .map(erro -> erro.getMessage())
                .collect(
                        Collectors.toList());
        return new ApiError(erros);
    }
}
